package ru.iit.system.active.management;

import ru.iit.system.active.management.model.EquipmentInProject;
import ru.iit.system.active.management.repository.EquipmentInProjectRepository;

import java.util.Random;

public class WorkerThread implements Runnable {

    private ApplicationConfig applicationConfig;

    private EquipmentInProjectRepository equipmentInProjectRepository;

    public WorkerThread(ApplicationConfig applicationConfig, EquipmentInProjectRepository equipmentInProjectRepository) {
        this.applicationConfig = applicationConfig;
        this.equipmentInProjectRepository = equipmentInProjectRepository;
    }

    public void run() {
        try {
            EquipmentInProject equipmentInProject = equipmentInProjectRepository.findOne(1L);
            EquipmentInProject equipmentInProjectLock =
                    applicationConfig.getEquipmentInProjectLock(equipmentInProject.getId());
            synchronized (equipmentInProjectLock) {
                System.out.print("[");
                System.out.print("Before some actions... ");
                Thread.sleep(1000);
                System.out.print("After some actions...");
                System.out.println("]");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
