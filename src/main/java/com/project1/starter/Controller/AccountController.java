package com.project1.starter.Controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;

import com.project1.starter.Models.Account;
import com.project1.starter.Models.Post;
import com.project1.starter.Services.AccountService;
import com.project1.starter.util.AppUtil;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {
    @Autowired
    private AccountService accountService;

    @Value("${spring.web.resources.static-locations}")
    private String photo_prefix;

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
    @PreAuthorize("isAuthenticated()")
    public String showProfile(Model model, Principal principal) {
        String authUser = principal != null ? principal.getName() : "email";

        Optional<Account> accountOptional = accountService.findOneByEmail(authUser);
        if (accountOptional.isPresent()) {
            Account acc = accountOptional.get();
            model.addAttribute("account", acc);
            model.addAttribute("photo", acc.getPhoto()); // if your template uses it
            return "account_views/profile";
        } else {
            return "redirect:/?error";
        }
    }

    @PostMapping("/profile")
    @PreAuthorize("isAuthenticated()")
    public String addposthandle(@Valid @ModelAttribute Account account, BindingResult result, Principal principal) {
        if (result.hasErrors()) {
            return "account_views/profile";
        }
        String authuser = "email";
        if (principal != null) {
            authuser = principal.getName();
        }
        Optional<Account> accountOptional = accountService.findOneByEmail(authuser);
        if (accountOptional.isPresent()) {
            Account accountid = accountService.findOneByid(account.getId()).get();
            accountid.setAge(account.getAge());
            accountid.setDate_of_birth(account.getDate_of_birth());
            accountid.setEmail(account.getEmail());
            accountid.setGender(account.getGender());
            accountid.setPassword(account.getPassword());
            accountid.setUsername(account.getUsername());

            accountService.save(accountid);
            SecurityContextHolder.clearContext();
            return "redirect:/";
        } else {
            return "redirect:/?error";
        }
    }

    @GetMapping("/editor")
    public String editor(Model model) {
        return "admin_views/editor";
    }

    @PostMapping("/update_photo")
    public String updatePhoto(@RequestParam("file") MultipartFile file,
            RedirectAttributes attributes,
            Principal principal) {

        if (file.isEmpty()) {
            attributes.addFlashAttribute("error", "No file uploaded.");
            return "redirect:/profile?error";
        }

        try {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String uniqueName = UUID.randomUUID() + "_" + fileName;

            // Save to ./uploads/ directory
            Path uploadPath = Paths.get("uploads/");
            Files.createDirectories(uploadPath); // create if it doesn't exist

            Path filePath = uploadPath.resolve(uniqueName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Update account
            String email = principal.getName();
            Optional<Account> optional = accountService.findOneByEmail(email);
            if (optional.isPresent()) {
                Account account = optional.get();
                account.setPhoto("/" + uniqueName); // or "/uploads/" + uniqueName if you prefer
                accountService.save(account);
            }

            attributes.addFlashAttribute("message", "Photo uploaded successfully.");
            return "redirect:/profile";

        } catch (IOException e) {
            e.printStackTrace();
            attributes.addFlashAttribute("error", "Failed to upload file.");
            return "redirect:/profile?error";
        }
    }

    @GetMapping("/forgot_password")
    public String forgotPassword(Model model) {
        return "auth_views/forgot_password";
    }

}
