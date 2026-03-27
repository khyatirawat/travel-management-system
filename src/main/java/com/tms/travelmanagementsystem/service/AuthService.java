package com.tms.travelmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tms.travelmanagementsystem.model.User;
import com.tms.travelmanagementsystem.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository repo;

    public String signup(User user) {
        repo.save(user);
        return "Signup successful";
    }

    public String login(User user) {
    User u = repo.findByEmail(user.getEmail())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (!u.getPwd().equals(user.getPwd())) {
            throw new RuntimeException("Invalid password");
        }

        return "success";
    }
}