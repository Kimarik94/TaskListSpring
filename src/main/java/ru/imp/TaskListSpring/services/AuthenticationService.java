package ru.imp.TaskListSpring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.imp.TaskListSpring.dtos.AuthenticationRequest;
import ru.imp.TaskListSpring.dtos.AuthenticationResponse;
import ru.imp.TaskListSpring.dtos.RegisterRequest;
import ru.imp.TaskListSpring.repositories.PersonRepository;
import ru.imp.TaskListSpring.models.Person;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var person = Person.builder()
                .name(request.getName())
                .age(request.getAge())
                .email(request.getEmail())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(personRepository.findByUsername(request.getUsername()).get().getRoles())
                .creatorTasks(new ArrayList<>())
                .executorTasks(new ArrayList<>())
                .build();
        personRepository.save(person);

        var jwtToken = jwtService.generateToken(person);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (
                    request.getUsername(),
                    request.getPassword()
                )
        );
        var person = personRepository.findByUsername(request.getUsername())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(person);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
