package com.taskmanager.task_manager.dashboard.service;

import com.taskmanager.task_manager.dashboard.dto.DashBoardDto;
import com.taskmanager.task_manager.dashboard.dto.DashBoardRequestDto;
import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import com.taskmanager.task_manager.task.service.TaskService;
import com.taskmanager.task_manager.users.model.Users;
import com.taskmanager.task_manager.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DashBoardService {
    // Get the object of task service
    private final TaskService taskService;
    //Get the object of user service
    private final UserService userService;
    @Autowired
    DashBoardService(TaskService taskService , UserService userService ){
        this.taskService = taskService;
        this.userService = userService;
    }


    public DashBoardDto getDashBoardData(DashBoardRequestDto dashBoardRequestDto){
        DashBoardDto dto = new DashBoardDto();

        // Get the login data
       Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication !=null;
        Users user = userService.getUserById(authentication.getName());
        // Get progress data
        Map<String, Double> progressData = taskService.getProgressData(dashBoardRequestDto.getCurrentDate() , user);
        dto.setProgressData(progressData);
        // Get the today's data
        List<TaskResponseDto> today = taskService.getAllTodayTask(dashBoardRequestDto.getCurrentDate() , user);
        dto.setTodayTask(today);
        // get weeks data
        List<TaskResponseDto> weekTasks = taskService.getAllWeekTask(dashBoardRequestDto.getCurrentDate() , user);
        dto.setWeekTask(weekTasks);
        // Get Month data
        List<TaskResponseDto> monthTasks = taskService.getAllMonthTask(dashBoardRequestDto.getCurrentDate() , user);
        dto.setMonthTask(monthTasks);

        return dto;
    }
}
