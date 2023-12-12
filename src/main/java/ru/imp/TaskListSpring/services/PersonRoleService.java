package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.PersonRole;
import ru.imp.TaskListSpring.repositories.PersonRoleRepository;

import java.util.HashSet;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PersonRoleService {

    private final PersonRoleRepository repository;

    @Autowired
    public PersonRoleService(PersonRoleRepository repository) {
        this.repository = repository;
    }

    public List<PersonRole> findAllRoles(){
        return repository.findAll();
    }

}
