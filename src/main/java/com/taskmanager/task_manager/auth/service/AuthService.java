package com.taskmanager.task_manager.auth.service;

import com.taskmanager.task_manager.auth.dto.SignUpDto;
import com.taskmanager.task_manager.users.model.Users;
import com.taskmanager.task_manager.users.service.UserService;
import org.apache.logging.log4j.util.InternalException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    // Get the user Service
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    AuthService(UserService userService , PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder  = passwordEncoder;
    }
    public void SignUp(SignUpDto signUpDto){
        if (userService.existsByEmail(signUpDto.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        Users user =  new Users(
                signUpDto.getUsername() ,
                signUpDto.getEmail(),
                signUpDto.getFullName() ,
                null ,
                passwordEncoder.encode(signUpDto.getPassword()),
               null

        );
        userService.createUser(user);

    }


}
