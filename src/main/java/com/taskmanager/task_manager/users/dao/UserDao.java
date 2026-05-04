package com.taskmanager.task_manager.users.dao;

import com.taskmanager.task_manager.users.model.Users;

public interface UserDao {
    Users findUserByUsername(String username);
}
