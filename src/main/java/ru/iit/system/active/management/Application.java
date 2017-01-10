package ru.iit.system.active.management;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.iit.system.active.management.model.Equipment;
import ru.iit.system.active.management.model.EquipmentInProject;
import ru.iit.system.active.management.model.Project;
import ru.iit.system.active.management.model.User;
import ru.iit.system.active.management.repository.EquipmentInProjectRepository;
import ru.iit.system.active.management.repository.EquipmentRepository;
import ru.iit.system.active.management.repository.ProjectRepository;
import ru.iit.system.active.management.repository.UserRepository;
import ru.iit.system.active.management.service.UserService;

import java.util.*;

public class Application {

    public static void main(String args[]) throws InterruptedException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        User user = new User();
        user.setId(1L);
        user.setLastName("Lastname");

        user = applicationContext.getBean(UserRepository.class).save(user);
        Thread.sleep(1000);

        user = applicationContext.getBean(UserRepository.class).findOne(user.getId());
        System.out.println(user.getLastName());

        Equipment equipment1 = new Equipment();
        equipment1.setCost(10.0);
        equipment1.setName("Светильники");
        equipment1.setTotalCount(1000L);

        Equipment equipment2 = new Equipment();
        equipment2.setCost(20.0);
        equipment2.setName("Стулья");
        equipment2.setTotalCount(2000L);

        equipment1 = applicationContext.getBean(EquipmentRepository.class).save(equipment1);

        Project project = new Project();
        project.setName("Test project");
        project.setStartDate(new GregorianCalendar(2017, 1, 5).getTime());
        project.setEndDate(new GregorianCalendar(2017, 1, 15).getTime());

        project = applicationContext.getBean(ProjectRepository.class).save(project);

        List<EquipmentInProject> equipmentInProjects = new ArrayList<>();
        for(int i = 0; i < 2; i++) {
            EquipmentInProject eq = new EquipmentInProject();
            eq.setEquipment(equipment1);
            eq.setProject(project);
            eq.setEquipmentCount(10L);
            equipmentInProjects.add(eq);
        }
        equipmentInProjects = applicationContext.getBean(EquipmentInProjectRepository.class).save(equipmentInProjects);

        applicationContext.getBean(ApplicationConfig.class).setEquipmentInProjectLocks(equipmentInProjects);
        Thread t1 = new Thread(
                new WorkerThread(applicationContext.getBean(ApplicationConfig.class),
                        applicationContext.getBean(EquipmentInProjectRepository.class)));
        Thread t2 = new Thread(
                new WorkerThread(applicationContext.getBean(ApplicationConfig.class),
                        applicationContext.getBean(EquipmentInProjectRepository.class)));
        Thread t3 = new Thread(
                new WorkerThread(applicationContext.getBean(ApplicationConfig.class),
                        applicationContext.getBean(EquipmentInProjectRepository.class)));
        Thread t4 = new Thread(
                new WorkerThread(applicationContext.getBean(ApplicationConfig.class),
                        applicationContext.getBean(EquipmentInProjectRepository.class)));
        Thread t5 = new Thread(
                new WorkerThread(applicationContext.getBean(ApplicationConfig.class),
                        applicationContext.getBean(EquipmentInProjectRepository.class)));
        Thread t6 = new Thread(
                new WorkerThread(applicationContext.getBean(ApplicationConfig.class),
                        applicationContext.getBean(EquipmentInProjectRepository.class)));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();

        Thread.currentThread().join();

        System.out.println("Count: " + equipmentInProjects.size());
    }
}
