package ru.imp.TaskListSpring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginPersonDto {
    private String name;
    private int age;
    private String username;
    private String password;
    private String email;
}
