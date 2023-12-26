package ru.imp.TaskListSpring.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.imp.TaskListSpring.models.Role;
import ru.imp.TaskListSpring.repositories.RoleRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository repository;

    public List<Role> findAllRoles() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Role findRoleByName(String roleName) {
        return (Role) repository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found with name: " + roleName));
    }
}
