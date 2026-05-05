package com.taskmanager.task_manager.task.model;

import com.taskmanager.task_manager.task.model.enums.Priority;
import com.taskmanager.task_manager.task.model.enums.Status;
import com.taskmanager.task_manager.task.model.enums.TaskType;
import com.taskmanager.task_manager.users.model.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;



    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "priority")
    @Enumerated(EnumType.STRING)
    private Priority priority;
    @Column(name = "due_date")
    private LocalDateTime dueDate ;
    @Column(name = "created_by")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "task_type")
    @Enumerated(EnumType.STRING)
    private TaskType taskType;



    @JoinColumn(name = "users")
    @ManyToOne
    private Users users;

    public Task(String title ,
                String description ,
                Priority priority ,
                LocalDateTime dueDate ,
                Status status ,
                TaskType taskType){
        this.title =  title;
        this.description = description;
        this.priority = priority;
        this.dueDate = dueDate;
        this.status = status;
        this.taskType = taskType;

    }

}
