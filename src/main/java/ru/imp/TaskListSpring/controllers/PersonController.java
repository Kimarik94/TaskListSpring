package ru.imp.TaskListSpring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController{
    @GetMapping("/personPage")
    public String getPersonDetails(){
        return null;
    }
}
