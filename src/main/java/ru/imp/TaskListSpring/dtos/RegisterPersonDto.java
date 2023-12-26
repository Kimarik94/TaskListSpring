package ru.imp.TaskListSpring.dtos;

import lombok.Data;

@Data
public class RegisterPersonDto {
    private String name;
    private int age;
    private String username;
    private String password;
    private String email;
}
