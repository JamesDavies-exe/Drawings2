package com.daviesjames.drawing2.controllers;

import com.daviesjames.drawing2.entities.User;
import org.springframework.ui.Model;
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

        //Pasarlo al UserService
        boolean passwordLength = false;
        if (password.length() < 5){
            passwordLength = true;
            model.addAttribute("passwordLength", passwordLength);
        }else {
            userService.saveUser(fullName, username, password);
            return "redirect:/login";
        }
        return "register";
    }


//    INICIO DE SESIÓN
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        User user = userService.checkUser(username, password);

        if (user != null){
            System.out.println(user.getFullName() + " quiere iniciar sesión");
        }

        return "login";
    }

}
