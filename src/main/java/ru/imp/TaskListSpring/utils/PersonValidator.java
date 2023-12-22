package ru.imp.TaskListSpring.utils;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.services.PersonService;

@Component
public class PersonValidator implements Validator {
    private final PersonService personService;

    public PersonValidator(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person = (Person) o;
        if(person.getName().isEmpty())
            errors.rejectValue("name", "", "User name can not be empty");
        if(person.getAge() == null)
            errors.rejectValue("age", "", "User age can not be empty");
        if(person.getEmail().isEmpty())
            errors.rejectValue("email", "", "User email can not be empty \n Or format not valid");
        if(person.getUsername().isEmpty())
            errors.rejectValue("username", "", "User username can not be empty \n Or format not valid");
        if(person.getPassword().isEmpty())
            errors.rejectValue("password", "", "User password can not be empty \n Or format not valid");
    }
}
