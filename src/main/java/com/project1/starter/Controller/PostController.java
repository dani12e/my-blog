package com.project1.starter.Controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.project1.starter.Models.Account;
import com.project1.starter.Models.Post;
import com.project1.starter.Services.AccountService;
import com.project1.starter.Services.PostService;

import jakarta.validation.Valid;

@Controller
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private AccountService accountService;

    @GetMapping("/post/view/{id}")
    public String getpost(@PathVariable Long id, Model model, Principal principal) {
        Optional<Post> optionalposts = postService.getbyid(id);
        String authuser = "email";
        if (optionalposts.isPresent()) {
            Post post = optionalposts.get();
            model.addAttribute("post", post);

            if (principal != null) {
                authuser = principal.getName();
            }
            if (authuser.equals(post.getAccount().getEmail())) {
                model.addAttribute("isOwner", true);
            } else {
                model.addAttribute("isOwner", false);
            }
            return "post_views/post";
        } else {
            return "404";
        }
    }

    @GetMapping("/add_post")
    public String addpost(Model model, Principal principal) {
        String authuser = "email";
        if (principal != null) {
            authuser = principal.getName();
        }
        Optional<Account> optionalAccount = accountService.findOneByEmail(authuser);

        if (optionalAccount.isPresent()) {
            Post post = new Post();
            post.setAccount(optionalAccount.get());
            model.addAttribute("post", post);
            return "post_views/add_post";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/add_post")
    @PreAuthorize("isAuthenticated()")
    public String addposthandle(@Valid @ModelAttribute Post post, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "post_views/add_post";
        }
        String authuser = "email";
        if (principal != null) {
            authuser = principal.getName();
        }
        if (post.getAccount().getEmail().compareToIgnoreCase(authuser) < 0) {
            return "redirect:/?error";
        }
        postService.save(post);

        return "redirect:/post/view/" + post.getId();
    }

    @GetMapping("/edit_post/view/{id}")
    @PreAuthorize("isAuthenticated()")
    public String geteditpost(@PathVariable Long id, Model model) {
        Optional<Post> optionalpost = postService.getbyid(id);
        if (optionalpost.isPresent()) {
            Post post = optionalpost.get();
            model.addAttribute("post", post);
            return "post_views/edit_post";
        } else {
            return "404";
        }
    }

    @PostMapping("/edit_post/view/{id}")
    @PreAuthorize("isAuthenticated()")
    public String updatePost(@Valid @ModelAttribute Post post, BindingResult result, @PathVariable Long id) {
        if (result.hasErrors()) {
            return "post_views/edit_post";
        }
        Optional<Post> optionalpos = postService.getbyid(id);
        if (optionalpos.isPresent()) {
            Post existingpost = optionalpos.get();
            existingpost.setTitle(post.getTitle());
            existingpost.setBody(post.getBody());
            postService.save(existingpost);
        }
        return "redirect:/post/view/" + post.getId();
    }

    @GetMapping("/delete_post/view/{id}")
    @PreAuthorize("isAuthenticated()")
    public String deletePost(@PathVariable Long id) {
        Optional<Post> optionalpostt = postService.getbyid(id);
        if (optionalpostt.isPresent()) {
            Post post = optionalpostt.get();
            postService.delete(post);
            return "redirect:/";
        } else {
            return "404";
        }
    }

}