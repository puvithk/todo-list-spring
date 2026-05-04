package com.taskmanager.task_manager.dashboard.dto;

import com.taskmanager.task_manager.task.dto.TaskResponseDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
@Data
public class DashBoardDto {

    private LocalDateTime currentTime ;
    private Map<String , Double> progressData;
    private List<TaskResponseDto> todayTask;
    private List<TaskResponseDto> weekTask;
    private List<TaskResponseDto> monthTask;
}
