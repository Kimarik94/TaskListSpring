package ru.imp.TaskListSpring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.imp.TaskListSpring.models.Person;

@Controller
public class WelcomePageController {
    @GetMapping("/")
    public String homepage(){
        return "welcome/homepage";
    }

    @GetMapping("/userInfo")
    public String showUserInfo(){

        return "welcome/homepage";
    }
}
