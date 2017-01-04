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

import java.util.Date;

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
        equipment2 = applicationContext.getBean(EquipmentRepository.class).save(equipment2);

        Project project = new Project();
        project.setName("Test project");
        project.setStartDate(new Date());
        project.setEndDate(new Date());

        project = applicationContext.getBean(ProjectRepository.class).save(project);

        EquipmentInProject eq1 = new EquipmentInProject();
        eq1.setEquipment(equipment1);
        eq1.setProject(project);
        eq1.setEquipmentCount(10L);

        EquipmentInProject eq2 = new EquipmentInProject();
        eq2.setEquipment(equipment2);
        eq2.setProject(project);
        eq2.setEquipmentCount(20L);

        eq1 = applicationContext.getBean(EquipmentInProjectRepository.class).save(eq1);
        eq2 = applicationContext.getBean(EquipmentInProjectRepository.class).save(eq2);

        System.out.println("=== The End ===");
    }
}
