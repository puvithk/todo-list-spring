package com.taskmanager.task_manager.users.service.impl;

import com.taskmanager.task_manager.users.dao.UserDao;
import com.taskmanager.task_manager.users.model.CustomUserDetails;
import com.taskmanager.task_manager.users.model.Users;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceDetails implements UserDetailsService {
    // Get Object user dto
    private final UserDao userDao;

    @Autowired
    UserServiceDetails(UserDao userDao)
    {
        this.userDao = userDao;
    }

    @Override
    @NonNull
    public UserDetails loadUserByUsername(@NonNull String username) throws UsernameNotFoundException {
        Users user = userDao.findUserByUsername(username);
        if(user==null){
            throw  new UsernameNotFoundException("User not found");
        }
        return new CustomUserDetails(user);
    }
}
