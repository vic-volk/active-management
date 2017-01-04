package ru.iit.system.active.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.iit.system.active.management.model.Equipment;

public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
}
