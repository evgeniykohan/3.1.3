package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String userInfo(Model model, Principal principal) {
        User currentUser = userService.findByUsername(principal.getName());
        model.addAttribute("curUser", currentUser);
        model.addAttribute("user", userService.oneUserInfo());
        return "userUser";
    }
}
