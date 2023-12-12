package ru.imp.TaskListSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.imp.TaskListSpring.models.PersonRole;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRole, Long> {
}
