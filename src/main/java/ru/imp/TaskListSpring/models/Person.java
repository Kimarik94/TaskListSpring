package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@Table(name = "persons")
@Setter
@Getter
@NoArgsConstructor
public class Person implements UserDetails {
    @Id
    @Column(name = "person_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "creator", fetch = FetchType.EAGER)
    private List<Task> creatorTasks;

    @OneToMany(mappedBy = "executor", fetch = FetchType.EAGER)
    private List<Task> executorTasks;

    @Column(name = "person_name")
    @NotEmpty(message = "Please input your full name!")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+ [A-Z]\\w+", message = "Expectation \"SecondName FirstName MiddleName\"")
    private String name;

    @Min(value = 10, message = "Age has to be bigger than 10")
    @Max(value = 65, message = "Age has to be less than 65")
    @NotEmpty(message = "Please input your age!")
    @Column(name = "person_age")
    private Integer age;

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

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
            name = "person_roles",
            joinColumns = @JoinColumn(name = "person_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    public Person(String name, int age, String email, String username, String password) {
        this.name = name;
        this.age = age;
        this.email = email;
        this.username = username;
        this.password = password;
        this.creatorTasks = new ArrayList<>();
        this.executorTasks = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.getRoles().stream()
                              .map(role -> new SimpleGrantedAuthority(role.getName()))
                              .collect(Collectors.toSet()
                              );
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
}
