package com.daviesjames.drawing2.controllers;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.repos.ImageRepo;
import com.daviesjames.drawing2.services.ImageService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import com.daviesjames.drawing2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.List;

@Controller
public class PaintController {
    ImageService imageService;
    PaintController(ImageService imageService){this.imageService = imageService;}

    @GetMapping("/paint")
    public String paint(Model model){
        return "paint";
    }

    @PostMapping("/paint")
    public String paint(Model model, @RequestParam String name, @RequestParam String jsonInfo,
                        HttpServletRequest req){
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");


        imageService.save(name, jsonInfo, userId);

        return "paint";
    }

    @GetMapping("myImages")
    public String myImages(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<Image> myImages = imageService.getMyImages(userId);
        model.addAttribute("imagesList",myImages);
        return "myImages";
    }

    @PostMapping("myImages")
    public String myImages(){
        return "myImages";
    }
}
