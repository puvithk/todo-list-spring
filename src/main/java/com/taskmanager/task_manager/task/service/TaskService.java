package com.taskmanager.task_manager.task.service;

import com.taskmanager.task_manager.task.dto.TaskRequestDto;
import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import com.taskmanager.task_manager.users.model.Users;

import java.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface TaskService {
    // Get the progress percentage of the progress bar
    Map<String , Double> getProgressData(LocalDate date , Users users);

    // Get all the List of all the today's task
    List<TaskResponseDto> getAllTodayTask(LocalDate date , Users users);

    // Get list of all this week task
    List<TaskResponseDto> getAllWeekTask(LocalDate date , Users users);

    // Get the list of all the task of this month
    List<TaskResponseDto> getAllMonthTask(LocalDate date , Users users);


    // Create a new task

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);

    void updateStatus(Long id, boolean completed);
}
