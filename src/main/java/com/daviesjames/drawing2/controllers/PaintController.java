package com.daviesjames.drawing2.controllers;

import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import com.daviesjames.drawing2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaintController {
    @GetMapping("/paint")
    public String paint(Model model){
        return "paint";
    }

    @PostMapping("/paint")
    public String paintP(Model model){
        return "paint";
    }
}
