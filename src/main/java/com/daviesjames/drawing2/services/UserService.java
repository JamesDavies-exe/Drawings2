package com.daviesjames.drawing2.services;

import com.daviesjames.drawing2.entities.User;
import com.daviesjames.drawing2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void saveUser(String fullName, String username, String password){
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setPassword(password);
        userRepo.saveUser(user);
    }
}