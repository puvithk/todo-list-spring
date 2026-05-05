package com.taskmanager.task_manager.users.service;

import com.taskmanager.task_manager.users.model.Users;

public interface UserService{
    Users getUserById(String username);

    void createUser(Users user);

    boolean existsByEmail(String username);
}
