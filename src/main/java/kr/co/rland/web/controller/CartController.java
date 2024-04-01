package kr.co.rland.web.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.service.MenuService;

@Controller
@RequestMapping("cart")
public class CartController {
    
    @Autowired
    MenuService menuService;

    @GetMapping("list")
    public String list (@CookieValue String menus
        , Model model){
            
            //decode
            String menusStr = URLDecoder.decode(menus, Charset.forName("utf-8"));
            System.out.println(menusStr);

            List<Menu> menuList;

            if(menus==null)
                menuList= new ArrayList<>();
            
            else{
                String menuStr = URLDecoder.decode(menus, Charset.forName("utf-8"));
                menuList = new Gson().fromJson(menuStr, List.class);
            }
            
            model.addAttribute(menus, menuList);

        return "cart/list";
    }

    @PostMapping("add-menu")
    public String addMenu(
            @RequestParam("id") Long id
            ,HttpServletResponse response 
            , @CookieValue(required = false) String menus
            ){
                List<Menu> menuList;
                
                //추가
                {
                    if(menus==null)
                        menuList= new ArrayList<>();
                    
                    else{
                        String menuStr = URLDecoder.decode(menus, Charset.forName("utf-8"));
                        menuList = new Gson().fromJson(menuStr, List.class);
                    }
                        Menu menu = menuService.getById(id);
                        menuList.add(menu);
                }

                //encode해서 보내기
                {
                    String menuStr = new Gson().toJson(menuList);
                    System.out.println(menuStr);
                    
                    String menuEncoded ="";

                    try{
                        menuEncoded = URLEncoder.encode(menuStr, "utf-8");
                    }catch(UnsupportedEncodingException e){
                        e.printStackTrace();
                    }
                    
                    Cookie menusCookie = new Cookie("menus", menuEncoded);
                    menusCookie.setPath("/");

                    response.addCookie(menusCookie);
                }

        return "redirect:/menu/list";
    }
}
