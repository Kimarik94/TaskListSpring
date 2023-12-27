package ru.imp.TaskListSpring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.imp.TaskListSpring.dtos.AuthenticationRequest;
import ru.imp.TaskListSpring.dtos.AuthenticationResponse;
import ru.imp.TaskListSpring.dtos.RegisterRequest;
import ru.imp.TaskListSpring.services.AuthenticationService;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

//    private final PersonService personService;
//    @GetMapping("/login")
//    public String login(){
//        return "/loginPage";
//    }
//
//    @RequestMapping("/")
//    public String index() {
//        return "redirect:/home";
//    }
//
//    @GetMapping("/registration")
//    public String onRegistration(@ModelAttribute("person") Person person){
//        return "registrationPage";
//    }
//
//    @PostMapping("/registration")
//    public String performRegistration(@ModelAttribute("person") @Valid Person person){
//        personService.save(person);
//        return "redirect:auth/login";

//    }
}
