package kr.co.rland.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeController {
    

    @GetMapping("index")
    public String index(Model model){
        model.addAttribute("m", "헬로우");
        return "index";
    }
}
