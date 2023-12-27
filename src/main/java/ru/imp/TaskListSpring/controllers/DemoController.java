package ru.imp.TaskListSpring.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/demo")
public class DemoController {
    @GetMapping
    public ResponseEntity<String> hello(){
        return ResponseEntity.ok("Hello from secured point");
    }
}
