package ru.imp.TaskListSpring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Role;
import ru.imp.TaskListSpring.services.PersonService;
import ru.imp.TaskListSpring.services.RoleService;
import ru.imp.TaskListSpring.utils.PersonValidator;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class PersonController{
    private final PersonService personService;
    private final RoleService roleService;
    private final PersonValidator personValidator;

    @GetMapping
    public String getPersonDetails(Model model, Authentication authentication){
        model.addAttribute("user", authentication.getPrincipal());
        model.addAttribute("allUsers", personService.findAll());
        return "users/allUsers";
    }

    @GetMapping("/{user_id}")
    public String shoUserById(@PathVariable("user_id") Long user_id, Model model){
        model.addAttribute("user", personService.findById(user_id));

        List<Role> roles = personService.findById(user_id).getRoles().stream().toList();
        model.addAttribute("roles", roles);
        return "users/userById";
    }

    @GetMapping("/{user_id}/editUser")
    public String editUser(@PathVariable("user_id") Long user_id, Model model){
        model.addAttribute("user", personService.findById(user_id));
        List<Role> roles = roleService.findAllRoles();
        model.addAttribute("allRoles", roles);
        return "users/editUser";
    }

    @PatchMapping("/{user_id}")
    public String update(@ModelAttribute("user") @Valid Person person,
                         @PathVariable("user_id") Long id, Model model,
                         BindingResult bindingResult){
        personValidator.validate(person, bindingResult);
        if(bindingResult.hasErrors()){
            List<Role> roles = roleService.findAllRoles();
            model.addAttribute("allRoles", roles);
            person.setId(id);
            model.addAttribute("user", person);
            return "users/editUser";
        }

        personService.updatePerson(id,person);
        return "redirect:/users";
    }

    @DeleteMapping("/{task_Id}")
    public String delete(@PathVariable("task_Id") Long id){
        personService.deletePerson(id);
        return "redirect:/users";
    }
}
