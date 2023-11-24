package com.daviesjames.drawing2.controllers;

import ch.qos.logback.core.model.Model;
import com.daviesjames.drawing2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class LoginAndRegisterController {

    UserService userService;

    LoginAndRegisterController(UserService userService){
        this.userService = userService;
    }

//    REGISTRO
    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/register")
    public String register(Model model, @RequestParam String fullName,
                                  @RequestParam String username, @RequestParam String password){

        userService.saveUser(fullName, username, password);
        return "register";
    }


//    INICIO DE SESIÓN
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        System.out.println(username);
        return "login";
    }

}
