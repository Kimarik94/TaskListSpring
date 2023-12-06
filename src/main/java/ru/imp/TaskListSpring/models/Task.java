package ru.imp.TaskListSpring.models;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import ru.imp.TaskListSpring.enums.TaskPriority;
import ru.imp.TaskListSpring.enums.TaskStatus;

import java.util.Date;

@Entity
@Table(name ="taskstable")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="task_id")
    private int id;

    @Column(name = "task_name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private Person creatorId;

    @Column(name = "task_creation_date")
    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Column(name = "task_body")
    private String body;

    @Column(name = "task_status")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "task_priority")
    @Enumerated(EnumType.STRING)
    private TaskPriority taskPriority;

    @Column(name = "task_edit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date editDate;

    @Column(name = "task_deadline")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date deadline;

    public Task() {

    }

    public Task(String name, String body, Date deadline) {
        this.name = name;
        this.body = body;
        this.taskStatus = TaskStatus.OPENED;
        this.taskPriority = TaskPriority.MEDIUM;
        this.editDate = null;
        this.deadline = deadline;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public Date getEditDate() {
        return editDate;
    }

    public void setEditDate(Date editDate) {
        this.editDate = editDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public TaskPriority getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(TaskPriority taskPriority) {
        this.taskPriority = taskPriority;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creatorId=" + creatorId +
                ", creationDate=" + creationDate +
                ", body='" + body + '\'' +
                ", taskStatus=" + taskStatus +
                ", taskPriority=" + taskPriority +
                ", editDate=" + editDate +
                ", deadline=" + deadline +
                '}';
    }
}