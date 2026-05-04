package com.taskmanager.task_manager.task.dto;

import com.taskmanager.task_manager.task.model.enums.Priority;
import com.taskmanager.task_manager.task.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class TaskResponseDto {
    private Long id ;
    private String title;
    private String description;
    private Priority priority;
    private LocalDateTime dueDate ;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Status status;

    private String users;
}
