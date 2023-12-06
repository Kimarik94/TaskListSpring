package ru.imp.TaskListSpring.models;

public class LoginResponseDTO {
    private Person person;
    private String jwt;

    public LoginResponseDTO(){
        super();
    }

    public LoginResponseDTO(Person person, String jwt){
        this.person = person;
        this.jwt = jwt;
    }

    public Person getPerson(){
        return this.person;
    }

    public void setUser(Person person){
        this.person = person;
    }

    public String getJwt(){
        return this.jwt;
    }

    public void setJwt(String jwt){
        this.jwt = jwt;
    }

}
