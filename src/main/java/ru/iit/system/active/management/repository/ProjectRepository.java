package ru.iit.system.active.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iit.system.active.management.model.Project;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
