package com.project1.starter.Controller;

import com.project1.starter.Models.Post;
import com.project1.starter.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String viewHomePage(Model model,
                               @RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size,
                               @RequestParam(defaultValue = "createdAt") String sortBy) {
        // Ensure no negative values
        page = Math.max(page, 0);
        size = Math.max(size, 1);

        try {
            Page<Post> postPage = postService.findAll(page, size, sortBy);

            model.addAttribute("postPage", postPage);
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", postPage.getTotalPages());
            model.addAttribute("sortBy", sortBy);
            model.addAttribute("size", size);
        } catch (Exception e) {
            model.addAttribute("errorMessage", "An error occurred while fetching posts.");
        }

        return "home_views/home";
    }
}
