package com.taskmanager.task_manager.users.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public Users(String username, String email, String fullname, String phone, String password, String profileUrl) {
        this.username = username;
        this.email = email;
        this.fullname = fullname;
        this.phone = phone;
        this.password = password;
        this.profileUrl = profileUrl;
    }

    @Column(name = "username")
    private String username ;
    @Column(name = "email")
    private String email ;
    @Column(name = "fullname")
    private String fullname;
    @Column(name = "phone")
    private String phone;
    @Column(name = "password")
    private String password ;
    @Column(name = "profile_url")
    private String profileUrl;
}
