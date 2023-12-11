package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

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

    public Task findById(int taskId) {
        Optional<Task> foundedTask = taskRepository.findById(taskId);
        return foundedTask.orElse(null);
    }

    @Transactional
    public void updateTask(int taskId, Task updatedTask) {
        updatedTask.setId(taskId);
        taskRepository.save(updatedTask);
    }

    @Transactional
    public void createTask(Task task) {
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(int taskId) {
        taskRepository.deleteById(taskId);
    }
}
