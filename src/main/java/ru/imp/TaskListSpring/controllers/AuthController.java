package ru.imp.TaskListSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    public AuthController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/login")
    public String login(){
        return "/loginPage";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/registration")
    public String onRegistration(@ModelAttribute("person") Person person){
        return "registrationPage";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person){
        String passwordFromUser = person.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(16);
        person.setPassword(encoder.encode(passwordFromUser));
        personService.save(person);
        return "redirect:/login";
    }
}
