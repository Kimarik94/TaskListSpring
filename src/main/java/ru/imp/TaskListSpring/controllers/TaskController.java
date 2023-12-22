package ru.imp.TaskListSpring.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import ru.imp.TaskListSpring.enums.TaskStatus;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.services.PersonService;
import ru.imp.TaskListSpring.services.TaskService;
import ru.imp.TaskListSpring.utils.TaskValidator;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("/alltasks")
public class TaskController {
    private final TaskService taskService;
    private final PersonService personService;
    private final TaskValidator taskValidator;

    @Autowired
    public TaskController(TaskService taskService, PersonService personService, PersonService personService1, TaskValidator taskValidator) {
        this.taskService = taskService;
        this.personService = personService1;
        this.taskValidator = taskValidator;
    }

    @GetMapping
    public String allTasks(Model model, Authentication authentication) {
        Person person = (Person) authentication.getPrincipal();
        model.addAttribute("createdTasks", taskService.findTasksByUserCreated(person));
        model.addAttribute("execTasks", taskService.findTasksByUserExec(person));
        return "tasks/allTasks";
    }

    @GetMapping("/{task_Id}")
    public String showTaskById(@PathVariable("task_Id") Long taskId, Model model) {
        model.addAttribute("task", taskService.findById(taskId));
        return "tasks/showTaskById";
    }

    @GetMapping("/new")
    public String newTask(Model model, Authentication authentication) {
        model.addAttribute("task", new Task());
        Person person = (Person) authentication.getPrincipal();
        model.addAttribute("person",person);
        model.addAttribute("allPersons", personService.findAll());
        return "tasks/newTask";
    }

    @Transactional
    @PostMapping
    public String createTask(@ModelAttribute("task") @Valid Task task,
                             BindingResult bindingResult, Model model, Authentication authentication) {
        taskValidator.validate(task,bindingResult);
        if(bindingResult.hasErrors()){
            Person executor = (Person) authentication.getPrincipal();
            model.addAttribute("person", executor);
            model.addAttribute("allPersons", personService.findAll());
            return "tasks/newTask";
        }
        Person creator = (Person) authentication.getPrincipal();
        Person executor = task.getExecutor();

        task.setCreationDate(new Date());
        task.setTaskStatus(TaskStatus.OPENED);
        task.setEditDate(new Date());
        task.setCreator(creator);
        task.setExecutor(executor);
        taskService.save(task);

        List<Task> creatorList = creator.getCreatorTasks();
        if(creatorList==null) creatorList = new ArrayList<>();
        creatorList.add(task);
        creator.setCreatorTasks(creatorList);

        List<Task> executorList = executor.getExecutorTasks();
        if(executorList == null) executorList = new ArrayList<>();
        executorList.add(task);
        executor.setExecutorTasks(executorList);

        return "redirect:/alltasks";
    }

    @GetMapping("/{task_Id}/editTask")
    public String editTask(Model model, @PathVariable("task_Id") Long taskId) {
        model.addAttribute("task", taskService.findById(taskId));
        model.addAttribute("person",taskService.findById(taskId).getCreator());
        model.addAttribute("allPersons", personService.findAll());

        return "tasks/editTask";
    }

    @PatchMapping("/{task_Id}")
    public String update(@ModelAttribute("task") @Valid Task task,
                         @PathVariable("task_Id") Long taskId, Model model, Authentication authentication,
                         BindingResult bindingResult){

        taskValidator.validate(task,bindingResult);

        if(bindingResult.hasErrors()){
            Person person = (Person) authentication.getPrincipal();
            model.addAttribute("person",person);
            model.addAttribute("allPersons", personService.findAll());
            task.setId(taskId);
            model.addAttribute("task", task);
            return "tasks/editTask";
        }
        taskService.updateTask(taskId,task);
        return "redirect:/alltasks";
    }

    @DeleteMapping("/{task_Id}")
    public String delete(@PathVariable("task_Id") Long id){
        taskService.deleteTask(id);
        return "redirect:/alltasks";
    }
}