package kr.co.rland.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import kr.co.rland.web.service.MemberService;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private MemberService service;

    @GetMapping("signin")
    public String signin(){
        return "user/signin";
    }

    @GetMapping("signup")
    public String signup(){
        return "user/signup";
    }

    @PostMapping("signin")
    public String signin(String username, String password
        //, HttpSession session
        , HttpServletResponse response
        ){
        
        //아이디와 비번이 일치하는 게 확인됨
        boolean valid = service.validate(username, password);
        
        //Member member = service.getByUsername();

        if(!valid)
            return "redirect:signin?error";
        
        Cookie uidCookie = new Cookie("uid","1");
        uidCookie.setPath("/"); //지역적사용가능
        //uidCookie.setMaxAge(100); //브라우저닫아도 쿠키사용가능 기한늘림
        //uidCookie.setSecure(false);
        //uidCookie.setHttpOnly(true);

        Cookie usernameCookie = new Cookie("username",username);
        usernameCookie.setPath("/");
        
        response.addCookie(uidCookie);
        response.addCookie(usernameCookie);
        // session.setAttribute("uid", member.getId());
        // session.setAttribute("username",username);
        
        return "redirect:/admin/index";
    }

}
