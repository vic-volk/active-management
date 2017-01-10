package ru.iit.system.active.management.service;

import org.springframework.stereotype.Service;
import ru.iit.system.active.management.repository.UserRepository;

import javax.inject.Inject;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;
}
