package ru.imp.TaskListSpring.services;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.imp.TaskListSpring.dtos.LoginPersonDto;
import ru.imp.TaskListSpring.dtos.RegisterPersonDto;
import ru.imp.TaskListSpring.models.Person;

@Service
public class AuthenticationService {
    private final PersonService personService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            PersonService personService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.personService = personService;
        this.passwordEncoder = passwordEncoder;
    }

    public Person signup(RegisterPersonDto personDto) {
        Person person = new Person();
        person.setName(personDto.getName());
        person.setAge(personDto.getAge());
        person.setUsername(personDto.getUsername());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setEmail(personDto.getEmail());

        return personService.save(person);
    }

    public Person authenticate(LoginPersonDto personDto) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        personDto.getUsername(),
                        personDto.getPassword()
                )
        );

        return personService.findByUsername(personDto.getUsername())
                .orElseThrow();
    }
}
