package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.imp.TaskListSpring.enums.TaskPriority;
import ru.imp.TaskListSpring.enums.TaskStatus;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Entity
@Table(name = "taskstable")
@Setter
@Getter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task_name")
    @NotEmpty(message = "Task name can not be empty")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Person creator;

    @ManyToOne
    @JoinColumn(name = "executor_id")
    private Person executor;


    @Column(name = "task_body")
    @NotEmpty(message = "Please fill task description")
    private String body;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "task_priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @Column(updatable = false, name = "task_creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "task_edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @Column(name = "task_deadline")
    @NotEmpty(message = "Task DeadLine can not be empty")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    public Task(String name, String body, Date deadline, Person creator) {
        this.name = name;
        this.body = body;
        this.taskStatus = TaskStatus.OPENED;
        this.taskPriority = TaskPriority.MEDIUM;
        this.editDate = null;
        this.deadline = deadline;
        this.creator = creator;
    }
}