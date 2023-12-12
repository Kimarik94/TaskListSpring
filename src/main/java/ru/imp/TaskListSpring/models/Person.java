package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "creatorId", fetch = FetchType.LAZY)
    private List<Task> creatorTasks;

    @Column(name = "person_name")
    @NotEmpty(message = "Please input your full name!")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Expectation \"SecondName FirstName MiddleName\"")
    private String name;

    @Min(value = 10, message = "Age has to be bigger than 10")
    @Max(value = 65, message = "Age has to be less than 65")
    @NotEmpty(message = "Please input your age!")
    @Column(name = "person_age")
    private int age;

    @NotEmpty(message = "Please input email-address")
    @Column(name = "person_email")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+", message = "Invalid email-format")
    private String email;

    @Column(name = "person_username")
    @NotEmpty(message = "Username has not to be empty")
    private String username;

    @Column(name = "person_password")
    @NotEmpty(message = "Field password has not to be empty")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER,cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinTable(
            name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<PersonRole> roles = new HashSet<>();

    public Person() {

    }

    public Person(String name, int age, String email, String username, String password) {
        this.creatorTasks = new ArrayList<>();
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Task> getCreatorTasks() {
        return creatorTasks;
    }

    public void setCreatorTasks(List<Task> creatorTasks) {
        this.creatorTasks = creatorTasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<PersonRole> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<PersonRole> roles) {
        this.roles = roles;
    }

    public void addRoleToPerson(PersonRole role){
        this.roles.add(role);
    }

    public void deleteRoleFromPerson(PersonRole role){
        this.roles.remove(role);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", creatorTasks=" + creatorTasks +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
