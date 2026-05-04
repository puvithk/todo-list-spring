package com.taskmanager.task_manager.home.controller;

import com.taskmanager.task_manager.auth.dto.LoginDto;
import com.taskmanager.task_manager.auth.dto.SignUpDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/taskmanager/{version}")
public class HomeController {
    @GetMapping(value = "/" , version = "1")
    public String home(Model model) {
        model.addAttribute("loginDto", new LoginDto());
        model.addAttribute("signUpDto", new SignUpDto());
        return "index";
    }
}
