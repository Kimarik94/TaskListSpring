package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PersonService implements UserDetailsService {

    private PasswordEncoder encoder;

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PasswordEncoder encoder, PersonRepository personRepository) {
        this.encoder = encoder;
        this.personRepository = personRepository;
    }

    public List<Person> findAllTasks(){
        return personRepository.findAll();
    }

    public Person findById(int taskId){
        Optional<Person> foundedPerson = personRepository.findById(taskId);
        return foundedPerson.orElse(null);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In the user details service");
        return personRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User is no valid"));
    }
}
