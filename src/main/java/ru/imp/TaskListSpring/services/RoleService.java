package ru.imp.TaskListSpring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Person;
import ru.imp.TaskListSpring.models.Role;
import ru.imp.TaskListSpring.repositories.RoleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class RoleService {

    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAllRoles() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findRoleByName(String roleName) {
        return (Role) repository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
    }
}
