package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.imp.TaskListSpring.enums.PersonRole;

import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "persons")
public class Person implements UserDetails {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "creatorId", fetch = FetchType.LAZY)
    private List<Task> creatorTasks;

    @Column(name="person_name")
    @NotEmpty(message = "Please input your full name!")
    //Full name had to be in format "Second Name +" "+ First Name+ " " + Middle Name
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Expectation \"SecondName FirstName MiddleName\"")
    private String name;

    @Min(value = 10, message = "Age has to be bigger than 10")
    @Max(value = 65, message = "Age has to be less than 65")
    @NotEmpty(message = "Please input your age!")
    @Column(name = "person_age")
    private int age;

    @NotEmpty(message = "Please input email-address")
    @Column(name = "person_email")
    @Pattern(regexp = "[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.[a-zA-Z0-9_-]+", message="Invalid email-format")
    private String email;

    @Column(name="person_username")
    @NotEmpty(message = "Username has not to be empty")
    private String username;

    @Column(name="person_password")
    @NotEmpty(message = "Field password has not to be empty")
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name="person_role",
            joinColumns = {@JoinColumn(name="person_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<PersonRole> authorities;

    public Person() {
        super();
        this.authorities = new HashSet<PersonRole>();
    }

    public Person(int id, List<Task> creatorTasks, String name, int age, String email, String username, String password, Set<PersonRole> authorities) {
        super();
        this.id = id;
        this.creatorTasks = creatorTasks;
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAuthorities(Set<PersonRole> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
}
