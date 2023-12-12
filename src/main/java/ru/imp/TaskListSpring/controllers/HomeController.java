package ru.imp.TaskListSpring.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.imp.TaskListSpring.security.PersonDetails;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String success(Model model, Authentication authentication) {
        PersonDetails personDetails = (PersonDetails) authentication.getPrincipal();
        model.addAttribute("person",personDetails.getPerson());

        return "/homePage";
    }
}
