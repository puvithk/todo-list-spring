package com.taskmanager.task_manager.auth.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SignUpDto {
    private String username;
    private String password;
    private String fullName;
    private String email;
    private MultipartFile profileImage;

}
