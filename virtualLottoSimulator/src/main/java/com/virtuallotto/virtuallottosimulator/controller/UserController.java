package com.virtuallotto.virtuallottosimulator.controller;

import com.virtuallotto.virtuallottosimulator.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.function.Supplier;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @PostMapping("/id-duplication-check")
    @ResponseBody
    public Object isNotIdDuplication(@RequestBody String id) {
        HashMap<String, Boolean> map = new HashMap<>();
        if(userService.findUser(id) == null) {
            System.out.println("id가 중복되었습니다.");
            map.put("result", false);
        } else {
            map.put("result", true);
        }
        return map;
    }

    @PostMapping("/signup")
    public String signup(UserDTO userForm) {
        String id = userForm.id();
        String password = userForm.password();
        userService.join(id, password);
        return "login";
    }

    @PostMapping("/login")
    @ResponseBody
    public Object login(UserDTO userForm) {
        HashMap<String, Boolean> map = new HashMap<>();
        String id = userForm.id();
        String password = userForm.password();

        if (userService.checkLogin(id, password)) {
            map.put("result", true);
            return map;
        }
        map.put("result", false);
        return map;
    }


    public <T> T readUserInput(Supplier<T> read) {
        try {
            return read.get();
        } catch (IllegalArgumentException e) {
            e.getMessage();
            return readUserInput(read);
        }
    }


}
