package com.taskmanager.task_manager.task.controller;

import com.taskmanager.task_manager.task.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taskmanager/{version}/api/task")
public class TaskRestController {
    private final TaskService taskService;
    TaskRestController(TaskService taskService){
        this.taskService = taskService;
    }
    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateTaskStatus(
            @PathVariable Long id,
            @RequestParam boolean completed
    ) {
        taskService.updateStatus(id, completed);
        return ResponseEntity.ok("Updated");
    }

}
