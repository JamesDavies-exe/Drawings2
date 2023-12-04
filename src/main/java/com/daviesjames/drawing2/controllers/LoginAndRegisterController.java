package com.daviesjames.drawing2.controllers;

import com.daviesjames.drawing2.Exceptions.InvalidPassword;
import com.daviesjames.drawing2.Exceptions.NoArguments;
import com.daviesjames.drawing2.Exceptions.UserIncorrect;
import com.daviesjames.drawing2.entities.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import com.daviesjames.drawing2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.http.HttpRequest;

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

boolean errorArgument = false;
boolean errorPassword = false;
        try {
            userService.saveUser(fullName, username, password);
            return "redirect:/login";
        }
        catch (NoArguments e){
            errorArgument = true;
            model.addAttribute("errorArgument", errorArgument);
            model.addAttribute("error", e.getMessage());
        }
        catch (InvalidPassword e){
            errorPassword = true;
            model.addAttribute("errorPassword", errorPassword);
            model.addAttribute("error", e.getMessage());
        }
        return "register";
    }


//    INICIO DE SESIÓN
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(Model model,@RequestParam String username, @RequestParam String password, HttpServletRequest req) throws UserIncorrect {
boolean userError = false;
        try{
            User user = userService.checkUser(username, password);
            HttpSession session = req.getSession();
            session.setAttribute("userId", user.getId());
            return "redirect:/paint";
        }
        catch (UserIncorrect e){
            userError = true;
            model.addAttribute("userError", userError);
            model.addAttribute("error", e.getMessage());
        }

        return "login";
    }

}
