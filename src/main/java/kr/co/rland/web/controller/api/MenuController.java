package kr.co.rland.web.controller.api;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.co.rland.web.entity.Menu;
import kr.co.rland.web.entity.MenuView;
import kr.co.rland.web.service.MenuService;

@RestController("apiMenuController")
@RequestMapping("/api/menus")
public class MenuController {
    
    @Autowired
    private MenuService service;

    @GetMapping
    public List<MenuView> list(
            @RequestParam(name="c", required=false) Long categoryId,
            @RequestParam(name="q", required=false) String query,
            @RequestParam(name="p", required=false, defaultValue="1") Integer page
            ){
        
        List<MenuView> menus = new ArrayList<>();

        if(categoryId!=null){
            menus=service.getList(page,categoryId);
        }
        else if (query!=null){
            menus=service.getList(page,query);
        }
        else {
            menus=service.getList(page);
        }

        return menus;
    }

    
    

    @GetMapping("1")
    public Menu get(Long id){
        return null;
    }

    @PostMapping
    public Menu add(Menu menu){
        return null;
    }

    @PutMapping //@PatchMapping
    public Menu edit(Menu menu){
        return null;
    }

    @DeleteMapping
    public String delete(Long id){
        return null;
    }

}
