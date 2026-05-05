package com.taskmanager.task_manager.task.controller;

import com.taskmanager.task_manager.dashboard.dto.DashBoardDto;
import com.taskmanager.task_manager.dashboard.dto.DashBoardRequestDto;
import com.taskmanager.task_manager.dashboard.service.DashBoardService;
import com.taskmanager.task_manager.task.dto.TaskRequestDto;
import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import com.taskmanager.task_manager.task.service.TaskService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
@RequestMapping("/taskmanager/{version}/task")
public class TaskController {
    @Value("${app.version}")
    private String version;

    // Get the requered bean
    private final TaskService taskService;
    // Get the dashboard bean
    private final DashBoardService dashBoardService ;
    TaskController(TaskService taskService , DashBoardService dashBoardService){
        this.taskService = taskService ;
        this.dashBoardService = dashBoardService;
    }
    @PostMapping(value = "/create" , version = "1")
    public String createTask(@ModelAttribute TaskRequestDto taskRequestDto) {
        taskService.createTask(taskRequestDto);
        return "redirect:/taskmanager/"+ version +"/dashboard/";
    }

}
