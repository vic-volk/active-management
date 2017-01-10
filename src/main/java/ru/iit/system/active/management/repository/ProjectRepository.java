package ru.iit.system.active.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.iit.system.active.management.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
