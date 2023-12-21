package ru.imp.TaskListSpring.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.services.TaskService;

@Component
public class TaskValidator implements Validator {

    private final TaskService taskService;

    public TaskValidator(TaskService taskService) {
        this.taskService = taskService;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Task.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Task task = (Task) o;
        if(task.getName().isEmpty()){
            errors.rejectValue("name","", "Name field is Empty");
        }
        if(task.getBody().isEmpty()){
            errors.rejectValue("body", "", "Description field is empty");
        }
        if(task.getDeadline()==null){
            errors.rejectValue("deadline", "", "Deadline field is empty");
        }

    }
}
