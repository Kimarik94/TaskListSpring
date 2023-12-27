package ru.imp.TaskListSpring.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginResponse {
    private String token;
    private long expiresIn;

    public String getToken() {
        return token;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}
