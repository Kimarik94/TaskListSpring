package ru.imp.TaskListSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.services.PersonService;

import javax.validation.Valid;

@Controller
public class AuthController {
    private final PersonService personService;

    @Autowired
    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login(){
        return "/loginPage";
    }

    @GetMapping("/registration")
    public String onRegistration(@ModelAttribute("person") Person person){
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person){
        personService.save(person);
        return "redirect:/login";
    }
}
