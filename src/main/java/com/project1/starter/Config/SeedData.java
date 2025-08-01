// package com.project1.starter.Config;

// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.CommandLineRunner;
// import org.springframework.stereotype.Component;

// import com.project1.starter.Models.Account;
// import com.project1.starter.Models.Authority;
// import com.project1.starter.Models.Post;
// import com.project1.starter.Services.AccountService;
// import com.project1.starter.Services.AuthorityService;
// import com.project1.starter.Services.PostService;
// import com.project1.starter.util.constants.Authorities;
// import com.project1.starter.util.constants.Roles;

// @Component
// public class SeedData implements CommandLineRunner {

//     @Autowired
//     private PostService postservice;

//     @Autowired
//     AccountService accountservice;

//     @Autowired
//     AuthorityService authorityService;

//     @Override
//     public void run(String... args) throws Exception {

//         for (Authorities auth : Authorities.values()) {
//             if (!authorityService.findByName(auth.getName()).isPresent()) {
//                 Authority authority = new Authority();
//                 authority.setName(auth.getName());
//                 authorityService.save(authority);
//             }
//         }

//         Account account01 = new Account();
//         Account account02 = new Account();
//         Account account03 = new Account();
//         Account account04 = new Account();

//         account01.setEmail("01email@gmail.com");
//         account01.setUsername("01");
//         account01.setPassword("password");
//         account01.setRole(Roles.USER.getRole());

//         account02.setEmail("02email@gmail.com");
//         account02.setUsername("02");
//         account02.setPassword("password");
//         account02.setRole(Roles.ADMIN.getRole());

//         account03.setEmail("03email@gmail.com");
//         account03.setUsername("03");
//         account03.setPassword("password");
//         account03.setRole(Roles.EDITOR.getRole());

//         account04.setEmail("04email@gmail.com");
//         account04.setUsername("04");
//         account04.setPassword("password");
//         account04.setRole(Roles.EDITOR.getRole());
//         Set<Authority> auths = new HashSet<>();
//         authorityService.findById(Authorities.ACCESS_ADMIN_PANEL.getId()).ifPresent(auths::add);
//         authorityService.findById(Authorities.RESET_USER_PASSWORD.getId()).ifPresent(auths::add);
//         account04.setAuths(auths);

//         accountservice.save(account01);
//         accountservice.save(account02);
//         accountservice.save(account03);
//         accountservice.save(account04);

//         List<Post> posts = postservice.getall();
//         if (posts.size() == 0) {
//             Post post01 = new Post();
//             post01.setBody("Post 01................................");
//             post01.setTitle("Post 01");
//             post01.setAccount(account01);
//             postservice.save(post01);

//             Post post02 = new Post();
//             post02.setBody("Post 02................................");
//             post02.setTitle("Post 02");
//             post02.setAccount(account02);
//             postservice.save(post02);
//         }

//     }

// }
