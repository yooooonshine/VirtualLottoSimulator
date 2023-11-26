package com.virtuallotto.virtuallottosimulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String login() {
        return "login";
    }

    @RequestMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    @RequestMapping("/lottoStore")
    public String lottoStore() {
        return "lottoStore";
    }
}
