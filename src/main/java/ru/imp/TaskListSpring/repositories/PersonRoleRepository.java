package ru.imp.TaskListSpring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.imp.TaskListSpring.enums.PersonRole;

import java.util.Optional;

@Repository
public interface PersonRoleRepository extends JpaRepository<PersonRole,Integer> {
    Optional<PersonRole> findByAuthority(String authority);
}
