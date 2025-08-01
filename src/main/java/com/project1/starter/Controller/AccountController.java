package com.project1.starter.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.project1.starter.Models.Account;
import com.project1.starter.Services.AccountService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping("/register")
    public String register(Model model) {
        Account account = new Account();
        model.addAttribute("account", account);
        return "auth_views/register";
    }

    @PostMapping("/register")
    public String register_user(@Valid @ModelAttribute Account account, BindingResult result) {
        if (result.hasErrors()) {
        return "auth_views/register";
       }
       accountService.save(account);
        
        return "redirect:/";
    }

      @GetMapping("/login")
    public String login(Model model) {
        return "auth_views/login";
    }

     @GetMapping("/profile")
    public String profile(Model model) {
        return "account_views/profile";
    }
     @GetMapping("/editor")
    public String editor(Model model) {
        return "admin_views/editor";
    }
    
    
}
