package kr.co.rland.web.controller;

import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.CategoryService;
import kr.co.rland.web.service.MenuService;

@Controller
@RequestMapping("menu")
public class MenuController {
    
    @Autowired
    private MenuService service;

    @Autowired
    private CategoryService CategoryService;

    @GetMapping("list")
    public String list(
            Model model,
            @CookieValue(required=false) String menusCookie,
            @RequestParam(name="q", required=false) String query,
            @RequestParam(name="c", required=false) Long categoryId,
            @RequestParam(name="p", required=false, defaultValue="1") Integer page
            ){
        
        List<MenuView> menus = new ArrayList<>();
        
        int count =0;

        if(categoryId!=null){
            menus=service.getList(page,categoryId);
            count = service.getCount(categoryId);
        }
        else if (query!=null){
            menus=service.getList(page,query);
            count = service.getCount(query);
        }
        else {
            menus=service.getList(page);
            count = service.getCount();
        }

        List<Category> categorys = CategoryService.getList();
        
        // 장바구니 쿠키 가져오기
        int cartTotalPrice=0;
        int cartCount=0;

        if(menusCookie!=null){
            
            Type listType = new TypeToken<List<Menu>>(){}.getType();

            String decoded = URLDecoder.decode(menusCookie, Charset.forName("utf-8"));

            Gson gson = new GsonBuilder()
                    .setDateFormat("MMM d, yyyy") // "2월 19, 2024"와 같은 형식에 대한 포매터 설정
                    .create();

            
            List<Menu> cartList = gson.fromJson(decoded, listType);
            
            cartCount=cartList.size();
            
            for(Menu m : cartList)
                cartTotalPrice += m.getPrice();
        
        }

        model.addAttribute("menus",menus);
        model.addAttribute("categorys",categorys);
        model.addAttribute("count",count);
        model.addAttribute("cartCount", cartCount);
        model.addAttribute("cartTotalPrice", cartTotalPrice);

        return "menu/list";
    }


    @GetMapping("detail")
    public String detail(Model model,@RequestParam("id") Long id){
        
        Menu menu = service.getById(id);
        model.addAttribute("menu",menu);
        
        return "menu/detail";
    }

    @GetMapping("add")
    public String add(){
        return "menu/add";
    }

    @PostMapping("addMenu")
    public String addMenu(Menu menu) {
        service.addMenu(menu);
        return "redirect:/menu/list";
    }

    @GetMapping("delete")
    public String deleteMenu(@RequestParam("id") String id_) {
        
        long id = Long.parseLong(id_);
        service.deleteMenu(id);

        return "redirect:/menu/list";
    }

    
    
}
