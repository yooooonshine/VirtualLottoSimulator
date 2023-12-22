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

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

    @RequestMapping("/lottoPurchased")
    public String lottoPurchased() {
        return "lottoPurchased";
    }

    @RequestMapping("/purchaseLotto")
    public String purchaseLotto() {
        return "purchaseLotto";
    }

    @RequestMapping("/instantLottoSimulator")
    public String instantLottoSimulator() {
        return "instantLottoSimulator";
    }
}
