package com.yaelbottwitter.twitterapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
class YaelBotController {

    @GetMapping("api/yaelbot")
    @ResponseBody
    public String index() {

        return "goto 'api/yaelbot' for twitter info";
    }

}