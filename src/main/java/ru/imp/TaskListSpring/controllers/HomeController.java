package ru.imp.TaskListSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.services.PersonDetailsServiceImpl;
import ru.imp.TaskListSpring.services.PersonService;

import java.util.Optional;

@Controller
public class HomeController {

    @GetMapping("/success")
    public String successPage(Model model, Authentication authentication) {
        PersonDetailsServiceImpl userDetails = (PersonDetailsServiceImpl) authentication.getPrincipal();

        return "welcome/homepage";
    }
}
