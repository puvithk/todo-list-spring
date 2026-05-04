package com.taskmanager.task_manager.auth.controller;

import com.taskmanager.task_manager.auth.dto.LoginDto;
import com.taskmanager.task_manager.auth.dto.SignUpDto;
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
    @GetMapping(value = "login" , version = "1")
    public String login(Model model , LoginDto loginDto){

        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("signUpDto", new SignUpDto());
        return "index.html";
    }

    @PostMapping(value = "signup" , version = "1")
    public String signUp( Model model,SignUpDto signUpDto) {
        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("signUpDto", new SignUpDto());
        MultipartFile file = signUpDto.getProfileImage();

        System.out.println("File name: " + file.getOriginalFilename());
        System.out.println("File size: " + file.getSize());


        return "redirect:/taskmanager/v1/";
    }

}
