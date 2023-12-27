package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.imp.TaskListSpring.enums.eRole;

@Entity
@Table(name = "roles")
@Setter
@Getter
@NoArgsConstructor
public class Role{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 20)
    private eRole name;

    public Role(eRole name){
        this.name = name;
    }
}
