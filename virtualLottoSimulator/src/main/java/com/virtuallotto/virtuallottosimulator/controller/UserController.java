package com.virtuallotto.virtuallottosimulator.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    @GetMapping("signup")
    public String signup() {
        return "signup";
    }

    //    @PostMapping("purchaseamount")
//    public String purchaseAmount() {
//
//    }
}
