package com.taskmanager.task_manager.auth.controller;

import com.taskmanager.task_manager.auth.dto.LoginDto;
import com.taskmanager.task_manager.auth.dto.SignUpDto;
import com.taskmanager.task_manager.auth.service.AuthService;
import com.taskmanager.task_manager.users.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Controller
@RequestMapping("/taskmanager/{version}/auth/")
public class AuthController {
    private final AuthService authService;



    @Autowired
    AuthController(AuthService authService ){
        this.authService = authService;

    }


    @GetMapping(value = "login" , version = "1")
    public String login(Model model , LoginDto loginDto){

        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("signUpDto", new SignUpDto());
        model.addAttribute("success" , false);
        return "index.html";
    }

    @PostMapping(value = "signup" , version = "1")
    public String signUp( Model model,SignUpDto signUpDto) {
        try{
            authService.SignUp(signUpDto);
        }catch (Exception e){
            return "redirect:/taskmanager/v1/auth/login?signup=true&error=true";
        }

        model.addAttribute("success" , true);
        return "redirect:/taskmanager/v1/auth/login?success=true";
    }

}
