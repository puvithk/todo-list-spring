package com.taskmanager.task_manager.users.service.impl;

import com.taskmanager.task_manager.exception.ResourceNotFound;
import com.taskmanager.task_manager.users.dao.UserDao;
import com.taskmanager.task_manager.users.model.Users;
import com.taskmanager.task_manager.users.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    // Get the  Object of user dao
    private final UserDao userDao ;


    @Autowired
    UserServiceImpl(UserDao userDao){
        this.userDao = userDao;
    }
    @Override
    public Users getUserById(String username) {
        Users user = userDao.findUserByUsername(username);
        if(user==null){
            throw new ResourceNotFound("User not found");
        }
        return user;
    }

    @Override
    @Transactional
    public void createUser(Users user) {
        userDao.save(user);
    }

    @Override
    public boolean existsByEmail(String username) {
        try {
            Users user = userDao.findUserByUsername(username);
            if(user!=null){
                return true;
            }
        }catch(Exception e){
            return false;
        }
        return false;
    }
}
