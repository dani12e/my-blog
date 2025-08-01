package com.project1.starter.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class AdminController {
    
     @GetMapping("/admin")
    public String register(Model model) {
        return "admin_views/admin";
    }
}
