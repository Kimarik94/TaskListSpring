package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Role;
import ru.imp.TaskListSpring.models.Task;
import ru.imp.TaskListSpring.repositories.PersonRepository;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class PersonService implements UserDetailsService {

    private final PersonRepository personRepository;
    private final RoleService roleService;

    @Autowired
    public PersonService(PersonRepository personRepository, RoleService personRoleService) {
        this.personRepository = personRepository;
        this.roleService = personRoleService;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long taskId) {
        Optional<Person> foundedPerson = personRepository.findById(taskId);
        return foundedPerson.orElse(null);
    }

    public Optional<Person> findByUsername(String username){
        return personRepository.findByUsername(username);
    }

    @Transactional
    public void save(Person person) {
        person.setCreatorTasks(new ArrayList<>());
        person.setExecutorTasks(new ArrayList<>());
        personRepository.save(person);
        Set<Role> defaultRoles = new HashSet<>();
        defaultRoles.add(roleService.findRoleByName("USER"));

        person.setRoles(defaultRoles);
    }

    @Transactional
    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> person = personRepository.findByUsername(username);
        if (person.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return person.get();
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
