package com.taskmanager.task_manager.task.dao.impl;

import com.taskmanager.task_manager.task.dao.TaskDao;
import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import com.taskmanager.task_manager.task.model.Task;
import com.taskmanager.task_manager.task.model.enums.TaskType;
import com.taskmanager.task_manager.users.model.Users;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Repository
public class TaskDaoImpl implements TaskDao {

    // Get Object of entity manager
    private final EntityManager entityManager;
    @Autowired
    TaskDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    @Override
    public List<Task> findAllTodaysTask(LocalDate date, Users users) {

        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.atTime(23, 59, 59);

        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM Task t WHERE t.users = :user AND t.dueDate BETWEEN :start AND :end",
                Task.class
        );

        query.setParameter("user", users);
        query.setParameter("start", start);
        query.setParameter("end", end);

        return query.getResultList();
    }

    @Override
    public List<Task> findAllWeeksTask(LocalDate startDate, LocalDate endDate, Users users) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM  Task t WHERE t.users =:user AND t.taskType =:taskType AND (t.dueDate BETWEEN :startDate AND :endDate)",
                Task.class
        );
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);
        query.setParameter("user" , users);
        query.setParameter("taskType" , TaskType.THIS_WEEK);
        query.setParameter("startDate" , start);
        query.setParameter("endDate" , end);


        return query.getResultList();
    }

    @Override
    public List<Task> findAllMonthTask(LocalDate startDate, LocalDate endDate, Users users) {
        TypedQuery<Task> query = entityManager.createQuery(
                "SELECT t FROM  Task t WHERE t.users =:user AND t.taskType =:taskType AND (t.dueDate BETWEEN :startDate AND :endDate)",
                Task.class
        );
        LocalDateTime start = startDate.atStartOfDay();
        LocalDateTime end = endDate.atTime(23, 59, 59);
        query.setParameter("user" , users);
        query.setParameter("taskType" , TaskType.THIS_MONTH);
        query.setParameter("startDate" , start);
        query.setParameter("endDate" , end);


        return query.getResultList();
    }

    @Override
    @Transactional
    public Task createTask(Task task, Users user) {
        task.setUsers(user);
        entityManager.persist(task);
        return task;
    }

    @Override
    public Task findById(Long id) {
        return entityManager.find(Task.class , id);
    }

    @Override
    @Transactional
    public void save(Task task) {
entityManager.persist(task);
    }
}
