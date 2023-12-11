package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAllTasks() {
        return personRepository.findAll();
    }

    public Person findById(int taskId) {
        Optional<Person> foundedPerson = personRepository.findById(taskId);
        return foundedPerson.orElse(null);
    }
}
