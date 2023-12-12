package ru.imp.TaskListSpring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.imp.TaskListSpring.enums.TaskPriority;
import ru.imp.TaskListSpring.enums.TaskStatus;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.services.TaskService;

import javax.validation.Valid;
import java.util.Date;

@Controller
@RequestMapping("/alltasks")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String allTasks(Model model) {
        model.addAttribute("tasks", taskService.findAllTasks());
        return "tasks/allTasks";
    }

    @GetMapping("/{task_Id}")
    public String showTaskById(@PathVariable("task_Id") Long taskId, Model model) {
        model.addAttribute("task", taskService.findById(taskId));
        return "tasks/showTaskById";
    }

    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "tasks/newTask";
    }

    @PostMapping()
    public String createTask(@ModelAttribute("task") @Valid Task task) {
        task.setCreationDate(new Date());
        task.setTaskStatus(TaskStatus.OPENED);
        task.setTaskPriority(TaskPriority.MEDIUM);
        task.setEditDate(new Date());

        taskService.createTask(task);
        return "redirect:/alltasks";
    }

    @GetMapping("/{task_Id}/edit")
    public String editTask(Model model, @PathVariable("task_Id") Long taskId) {
        model.addAttribute("task", taskService.findById(taskId));
        return "tasks/editTask";
    }
}