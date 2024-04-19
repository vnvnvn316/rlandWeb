package kr.co.rland.web.controller.admin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import jakarta.servlet.http.HttpServletRequest;
import kr.co.rland.web.config.security.WebUserDetails;
import kr.co.rland.web.entity.Category;
import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.CategoryService;
import kr.co.rland.web.service.MenuService;

@Controller("adminMenuController")
@RequestMapping("admin/menu")
public class MenuController {
    @Autowired
    private MenuService service;

    @Autowired
    private CategoryService CategoryService;

    @PostMapping("reg")
    public String reg(
        Menu menu
        ,@RequestParam("img-file") List<MultipartFile> imgFiles
        , HttpServletRequest request
        , Principal principal
    ) throws IllegalStateException, IOException {
        

        List<String> fileNames = new ArrayList<>();

        for(MultipartFile imgFile : imgFiles)
        {
            String fileName="";
                //파일저장
                if(imgFile!= null && !imgFile.isEmpty())
                {
                    fileName = imgFile.getOriginalFilename();

                    String path = "/image/menu";
                    String realPath = request
                                        .getServletContext()
                                        .getRealPath(path);

                    System.out.println("realPath : "+realPath);
                    File pathFile = new File(realPath);

                    if(!pathFile.exists())
                        pathFile.mkdirs();

                    File file = new File(realPath+File.separator+fileName);
                    
                    //파일을 폴더에 저장
                    imgFile.transferTo(file);
                    
                    fileNames.add(fileName);
                
                }
       
        }
            menu.setRegMemberId(160L);
            menu.setCategoryId(1L);
            //menu.setImg(fileName);

            int affected = service.add(menu, fileNames);

            System.out.println("=====================");
             System.out.printf("imgFlies:%s\n",imgFiles);
             System.out.printf("affected:%d\n",affected);

             System.out.println(menu);

        return "redirect:list";
    }


    @GetMapping("reg")
    public String reg() {

        return "admin/menu/reg";
    }

    // @GetMapping("list")
    // public String list() {

    //     return "admin/menu/list";
    // }

    @GetMapping("list")
    public String list(
            Model model,
            @CookieValue(required=false) String menusCookie,
            @RequestParam(name="q", required=false) String query,
            @RequestParam(name="c", required=false) Long categoryId,
            @RequestParam(name="p", required=false, defaultValue="1") Integer page,
            @AuthenticationPrincipal WebUserDetails userDetails
            ){
        
        List<MenuView> menus = new ArrayList<>();
        
        int count =0;

        Long memberId=null;
        if(userDetails!=null)
            memberId = userDetails.getId();

        if(categoryId!=null){
            menus=service.getList(memberId,page,categoryId);
            count = service.getCount(categoryId);
        }
        else if (query!=null){
            menus=service.getList(memberId,page,query);
            count = service.getCount(query);
        }
        else {
            menus=service.getList(memberId,page);
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

        return "admin/menu/list";
    }
}
