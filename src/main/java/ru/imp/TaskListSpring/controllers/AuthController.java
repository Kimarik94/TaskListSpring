package ru.imp.TaskListSpring.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.imp.TaskListSpring.dtos.LoginPersonDto;
import ru.imp.TaskListSpring.dtos.LoginResponse;
import ru.imp.TaskListSpring.dtos.RegisterPersonDto;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.services.AuthenticationService;
import ru.imp.TaskListSpring.services.JwtService;
import ru.imp.TaskListSpring.services.PersonService;

import javax.validation.Valid;

@RequestMapping("/auth")
@Controller
@RequiredArgsConstructor
public class AuthController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;
    private final PersonService personService;

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
        personService.save(person);
        return "redirect:auth/login";
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createNewUser(@RequestBody RegisterPersonDto registerPersonDto){
        Person registeredPerson = authenticationService.signup(registerPersonDto);
        return ResponseEntity.ok(registeredPerson);
    }

    @PostMapping("/login")
    public String authenticate(@RequestBody LoginPersonDto loginPersonDto) {

        Person authenticatedPerson = authenticationService.authenticate(loginPersonDto);

        String jwtToken = jwtService.generateToken(authenticatedPerson);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        System.out.println(ResponseEntity.ok(loginResponse));

        return "redirect:/home";
    }
}
