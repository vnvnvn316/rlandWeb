package kr.co.rland.web.controller.admin;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller("adminHomeController")
@RequestMapping("admin")
public class HomeController {
    
    @GetMapping("index")
    public String index(HttpSession session
        ,HttpServletRequest request
        , @CookieValue(required = false) Long uid
        , Principal principal
    ) {
        
        //방법1
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        //String username = auth.getName();
        //System.out.println(username);

        //방법2
        String username = principal.getName();
        System.out.println(username);


        // if(session.getAttribute("uid")==null)
        //     return "redirect:/user/signin";

        // if(uid==null)
        //     return "redirect:/user/signin";

            return "admin/index";
    }
    
}