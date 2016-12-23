package ru.iit.system.active.management;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.iit.system.active.management.model.User;
import ru.iit.system.active.management.repository.UserRepository;
import ru.iit.system.active.management.service.UserService;

public class Application {

    public static void main(String args[]) throws InterruptedException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfig.class);

        User user = new User();
        user.setId(1L);
        user.setLastname("Lastname");

        user = applicationContext.getBean(UserRepository.class).save(user);
        Thread.sleep(1000);

        user = applicationContext.getBean(UserRepository.class).findOne(user.getId());
        System.out.println(user.getLastname());

        UserService userService = applicationContext.getBean(UserService.class);

    }
}
