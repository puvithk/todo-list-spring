package com.taskmanager.task_manager.dashboard.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DashBoardRequestDto {
    private LocalDate currentDate ;
}
