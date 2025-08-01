package com.project1.starter.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project1.starter.Models.Post;
import com.project1.starter.Services.PostService;



@Controller
public class HomeController {
    @Autowired
    private PostService postService;
   @GetMapping("/")
    public String home(Model model){
        List <Post> posts = postService.getall();
        model.addAttribute("posts", posts);
        return "home_views/home";
    }

    
}
