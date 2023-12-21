package ru.imp.TaskListSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

}