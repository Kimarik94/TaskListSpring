package ru.imp.TaskListSpring.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.services.PersonService;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String success(Model model, Authentication authentication) {
        Person person = (Person) authentication.getPrincipal();
        model.addAttribute("person",person);
        return "/homePage";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login?logout";
    }
}
