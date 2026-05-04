package com.taskmanager.task_manager.dashboard.controller;

import com.taskmanager.task_manager.dashboard.dto.DashBoardDto;
import com.taskmanager.task_manager.dashboard.dto.DashBoardRequestDto;
import com.taskmanager.task_manager.dashboard.service.DashBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Date;

@Controller
@RequestMapping("/taskmanager/{version}/dashboard/")
public class DashboardController {
    // Declare a dashboard service
    private final DashBoardService dashBoardService;
    @Autowired
    DashboardController(DashBoardService dashBoardService){
        this.dashBoardService = dashBoardService;
    }


    @GetMapping(value = "/" , version = "1")
    public String dashboard(Model model){
        DashBoardRequestDto dashBoardRequestDto = new DashBoardRequestDto();
        dashBoardRequestDto.setCurrentDate(LocalDate.now());
        DashBoardDto dashBoardDto = dashBoardService.getDashBoardData(dashBoardRequestDto);

        System.out.println(dashBoardDto.toString());


        model.addAttribute("data" ,dashBoardDto);
        return "todo-list";
    }

}