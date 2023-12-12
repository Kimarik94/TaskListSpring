package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class PersonRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long id;

    @Column(name = "role_name")
    private String name;

    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "roles")
    Set<Person> personsByRole;

    public PersonRole() {
    }

    public PersonRole(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Person> getPersonsByRole() {
        return personsByRole;
    }

    public void setPersonsByRole(Set<Person> personsByRole) {
        this.personsByRole = personsByRole;
    }

    @Override
    public String toString() {
        return "PersonRole{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public String getAuthority() {
        return this.getName();
    }
}
