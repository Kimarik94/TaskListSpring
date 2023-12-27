package ru.imp.TaskListSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.imp.TaskListSpring.enums.eRole;
import ru.imp.TaskListSpring.models.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(eRole roleName);
}
