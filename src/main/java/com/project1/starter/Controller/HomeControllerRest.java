package com.project1.starter.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1.starter.Services.PostService;

@RestController
@RequestMapping("/api/v1")
public class HomeControllerRest {

    @Autowired
    private PostService postService;

    @GetMapping("/")
    public String viewHomePage() {
      

        Logger logger= LoggerFactory.getLogger(HomeControllerRest.class);

        logger.error("we fucked");

        return "testtttt";
    }
}
