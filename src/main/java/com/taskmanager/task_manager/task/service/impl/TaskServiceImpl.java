package com.taskmanager.task_manager.task.service.impl;

import com.taskmanager.task_manager.task.dao.TaskDao;
import com.taskmanager.task_manager.task.dto.TaskRequestDto;
import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import com.taskmanager.task_manager.task.model.Task;
import com.taskmanager.task_manager.task.model.enums.Status;
import com.taskmanager.task_manager.task.service.TaskService;
import com.taskmanager.task_manager.users.model.Users;

import com.taskmanager.task_manager.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class TaskServiceImpl implements TaskService {
    // Get the bean of task dao
    private final TaskDao taskDao;
    // Get the user service
    private final UserService userService;
    @Autowired
    TaskServiceImpl(TaskDao taskDao , UserService userService){
        this.taskDao = taskDao;
        this.userService = userService;
    }
    @Override
    public Map<String, Double> getProgressData(LocalDate date, Users users) {

            Map<String, Double> progress = new HashMap<>();

            List<TaskResponseDto> todayTask = getAllTodayTask(date, users);
            List<TaskResponseDto> weeksTask = getAllWeekTask(date, users);
            List<TaskResponseDto> monthTask = getAllMonthTask(date, users);

            progress.put("todayProgress", calculateProgress(todayTask)*100);
            progress.put("weekProgress", calculateProgress(weeksTask)*100);
            progress.put("monthProgress", calculateProgress(monthTask)*100);

            return progress;
        }




    @Override
    public List<TaskResponseDto> getAllTodayTask(LocalDate date, Users users) {
        List<Task> tasks = taskDao.findAllTodaysTask(date , users);


        return  tasks.stream()
                .map(task -> new TaskResponseDto(
                        task.getId(),
                        task.getTitle() ,
                        task.getDescription() ,
                        task.getPriority() ,
                        task.getDueDate(),
                        task.getCreatedAt(),
                        task.getUpdatedAt() ,
                        task.getStatus(),
                        task.getUsers().getUsername()
                )).toList();
    }

    @Override
    public List<TaskResponseDto> getAllWeekTask(LocalDate date, Users users) {

        LocalDate startDate = date.with(DayOfWeek.MONDAY);
        LocalDate endDate = date.with(DayOfWeek.SUNDAY);

        List<Task> tasks = taskDao.findAllWeeksTask(startDate, endDate , users);

        return  tasks.stream()
                .map(task -> new TaskResponseDto(
                        task.getId(),
                        task.getTitle() ,
                        task.getDescription() ,
                        task.getPriority() ,
                        task.getDueDate(),
                        task.getCreatedAt(),
                        task.getUpdatedAt() ,
                        task.getStatus(),
                        task.getUsers().getUsername()
                )).toList();
    }

    @Override
    public List<TaskResponseDto> getAllMonthTask(LocalDate date, Users users) {

        LocalDate startDate = date.withDayOfMonth(1);
        LocalDate endDate = date.withDayOfMonth(date.lengthOfMonth());

        List<Task> tasks = taskDao.findAllMonthTask(startDate , endDate, users);

        return  tasks.stream()
                .map(task -> new TaskResponseDto(
                        task.getId(),
                        task.getTitle() ,
                        task.getDescription() ,
                        task.getPriority() ,
                        task.getDueDate(),
                        task.getCreatedAt(),
                        task.getUpdatedAt() ,
                        task.getStatus(),
                        task.getUsers().getUsername()
                )).toList();
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication==null){
            throw new com.taskmanager.task_manager.exception.Authentication("Loing and continue");
        }
        Users user = userService.getUserById(authentication.getName());
        Task task =  new Task(
                taskRequestDto.getTitle() ,
                taskRequestDto.getDescription(),
                taskRequestDto.getPriority(),
                taskRequestDto.getDueDate(),
                taskRequestDto.getStatus(),
                taskRequestDto.getTaskType()

        );
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        Task taskResult =  taskDao.createTask(task , user);
        return new TaskResponseDto(
                taskResult.getId(),
                taskResult.getTitle(),
                taskResult.getDescription() ,
                taskResult.getPriority(),
                taskResult.getDueDate(),
                taskResult.getCreatedAt() ,
                taskResult.getUpdatedAt() ,
                taskResult.getStatus(),
                taskResult.getUsers().getUsername()
        );
    }

    // helper function
    private double calculateProgress(List<TaskResponseDto> tasks) {
        if (tasks == null || tasks.isEmpty()) return 0.0;

        long completed = tasks.stream()
                .filter(task -> task.getStatus() == Status.COMPLETED)
                .count();

        return (double) completed / tasks.size();
    }
}
