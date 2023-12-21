package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.repositories.TaskRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> findAllTasks() {
        return taskRepository.findAll();
    }

    public Task findById(Long taskId) {
        Optional<Task> foundedTask = taskRepository.findById(taskId);
        return foundedTask.orElse(null);
    }

    public List<Task> findTasksByUserCreated(Person person){
        List<Task> list = taskRepository.findAll().stream().filter(e -> Objects.equals(e.getCreator().getId(), person.getId())).toList();
        return list;
    }

    public List<Task> findTasksByUserExec(Person person){
        List<Task> list = taskRepository.findAll().stream().filter(e -> Objects.equals(e.getExecutor().getId(), person.getId())).toList();
        return list;
    }

    @Transactional
    public void save(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void updateTask(Long taskId, Task updatedTask) {
        Task tempTask = findById(taskId);
        updatedTask.setId(taskId);
        updatedTask.setCreator(tempTask.getCreator());
        updatedTask.setCreationDate(tempTask.getCreationDate());
        updatedTask.setEditDate(new Date());

        updatedTask.setEditDate(new Date());
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }
}
