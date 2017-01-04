package ru.iit.system.active.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iit.system.active.management.model.EquipmentInProject;

public interface EquipmentInProjectRepository extends JpaRepository<EquipmentInProject, Long> {
}
