package ru.imp.TaskListSpring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.dtos.RegisterPersonDto;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Role;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.repositories.PersonRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final RoleService roleService;
    private final TaskService taskService;

    public Optional<Person> findByUsername(String username){
        return personRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format("User '%s' not found", username));
        }
        return person.get();
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long taskId) {
        Optional<Person> foundedPerson = personRepository.findById(taskId);
        return foundedPerson.orElse(null);
    }

    @Transactional
    public Person save(Person person) {
        person.setCreatorTasks(new ArrayList<>());
        person.setExecutorTasks(new ArrayList<>());

        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleService.findRoleByName("ROLE_USER"));
        person.setRoles(defaultRoles);

        return personRepository.save(person);
    }

    @Transactional
    public Person save(RegisterPersonDto registerPersonDto) {
        Person newPerson = new Person();
        newPerson.setName(registerPersonDto.getName());
        newPerson.setAge(registerPersonDto.getAge());
        newPerson.setUsername(registerPersonDto.getUsername());
        newPerson.setEmail(registerPersonDto.getEmail());

        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleService.findRoleByName("ROLE_USER"));

        newPerson.setRoles(defaultRoles);

        return personRepository.save(newPerson);
    }

    @Transactional
    public void deletePerson(Long personId) {
        Person person = findById(personId);
        List<Task> createdTasks = person.getCreatorTasks();
        List<Task> executionTasks = person.getExecutorTasks();
        if(createdTasks.isEmpty() && executionTasks.isEmpty()) personRepository.deleteById(personId);
        else{
            for(Task task : createdTasks){
                if (task.getCreator() == person && task.getExecutor() == person) taskService.deleteTask(task.getId());
                if (task.getCreator() == person && task.getExecutor() != person) task.setCreator(task.getExecutor());
                if (task.getCreator() != person && task.getExecutor() == person) task.setExecutor(task.getCreator());
            }
        }
        personRepository.deleteById(personId);
    }

    @Transactional
    public void updatePerson(Long personId, Person updatedPerson) {
        Person originalPerson = findById(personId);
        updatedPerson.setId(personId);
        updatedPerson.setCreatorTasks(originalPerson.getCreatorTasks());
        updatedPerson.setExecutorTasks(originalPerson.getExecutorTasks());

        personRepository.save(updatedPerson);
    }
}
