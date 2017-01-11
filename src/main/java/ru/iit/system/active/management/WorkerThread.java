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
        while (true) {
            try {
                long randomId = new Random().nextInt(2) + 1;
                EquipmentInProject equipmentInProject = equipmentInProjectRepository.findOne(randomId);
                EquipmentInProject equipmentInProjectLock =
                        applicationConfig.getEquipmentInProjectLock(equipmentInProject.getId());
                synchronized (equipmentInProjectLock) {
                    System.out.format("ThreadId - [%s]; Pool id [%s]; ", Thread.currentThread().getId(), randomId);
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
}
