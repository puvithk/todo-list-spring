package com.taskmanager.task_manager.task.dao;

import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import com.taskmanager.task_manager.task.model.Task;
import com.taskmanager.task_manager.users.model.Users;

import java.time.LocalDate;
import java.util.List;

public interface TaskDao {
    List<Task> findAllTodaysTask(LocalDate date, Users users);

    List<Task> findAllWeeksTask(LocalDate startDate, LocalDate endDate, Users users);

    List<Task> findAllMonthTask(LocalDate startDate, LocalDate endDate,Users users);

    Task createTask(Task task, Users user);
}
