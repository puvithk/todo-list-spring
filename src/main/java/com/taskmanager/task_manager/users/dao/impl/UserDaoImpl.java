package com.taskmanager.task_manager.users.dao.impl;

import com.taskmanager.task_manager.users.dao.UserDao;
import com.taskmanager.task_manager.users.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {
    // Object of enity manager
    private final EntityManager entityManager;
    @Autowired
    UserDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }
    @Override
    public Users findUserByUsername(String username) {

        TypedQuery<Users> query = entityManager.createQuery(
                "SELECT u FROM Users u WHERE u.username =:username" ,
                Users.class
        );
        query.setParameter("username" , username);
        return query.getSingleResult();
    }

    @Override
    public void save(Users user) {
        entityManager.persist(user);
    }
}
