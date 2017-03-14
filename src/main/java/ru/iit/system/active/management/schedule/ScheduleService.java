package ru.iit.system.active.management.schedule;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ScheduleService {

    @PostConstruct
    public void init() {
        System.out.println("Scheduler initialized");
    }

    @Scheduled(cron="${schedule.service.cron}")
    public void testAsync() {
        System.out.println("scheduled execution");
    }
}
