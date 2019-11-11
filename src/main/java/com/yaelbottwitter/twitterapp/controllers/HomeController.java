package com.yaelbottwitter.twitterapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "goto 'api/yaelbot' for twitter info";
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

}