package com.taskmanager.task_manager.task.dto;

import com.taskmanager.task_manager.task.model.enums.Priority;
import com.taskmanager.task_manager.task.model.enums.Status;
import com.taskmanager.task_manager.task.model.enums.TaskType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskRequestDto {
    private String title;
    private String description;
    private Priority priority;
    private LocalDateTime dueDate ;
    private Status status;
    private TaskType taskType;

}
