package com.daviesjames.drawing2.controllers;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.entities.Version;
import com.daviesjames.drawing2.repos.ImageRepo;
import com.daviesjames.drawing2.services.ImageService;
import com.daviesjames.drawing2.services.VersionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.ui.Model;
import com.daviesjames.drawing2.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@Controller
public class PaintController {
    ImageService imageService;

    VersionService versionService;

    UserService userService;
    PaintController(ImageService imageService, UserService userService, VersionService versionService){
        this.imageService = imageService;
        this.userService = userService;
        this.versionService = versionService;
    }

    @GetMapping("/paint")
    public String paint(Model model){
        return "paint";
    }

    //Crea una imagen nueva con el nombre inidicado y las figuras

    @PostMapping("/paint")
    public String paint(Model model, @RequestParam String name, @RequestParam String jsonInfo,
                        HttpServletRequest req){
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");

        if(Objects.equals(name, "")){
            name = imageService.createRandomFileName(userId);
        }

        //Mirar si el nombre que ya está puesto existe. Si es así, lanzar un error (en el servicio)


        int imageId = imageService.save(name, userId);
        versionService.addVersion(imageId, jsonInfo);


        return "paint";
    }

    //Devuelve la lista de todas las imagenes que coincidian con el id del usuario
    @GetMapping("/myImages")
    public String myImages(Model model, HttpServletRequest req){
        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<Image> myImages = imageService.getMyImages(userId);
        model.addAttribute("imagesList",myImages);
        return "myImages";
    }


    @PostMapping("/myImages")
    public String myImages(){
        return "myImages";
    }

    @GetMapping("/community")
    public String community(Model model){
        List<Image> allImages = imageService.getAllImages();
        model.addAttribute("communityList", allImages);
        return "community";
    }

    @GetMapping("/view")
    public String view(){return "view";}

    //Devuelve el id y las figuras que han solicitado ver
    @PostMapping("/view")
    public String view(Model model, @RequestParam int imageId){
        System.out.println("Quiero ver " + imageId);
        Version lastVersion = versionService.getByImageId(imageId);
        List<Version> versionList = versionService.getAllVersionsByImageId(String.valueOf(imageId));

        model.addAttribute("imageData", lastVersion.getJsonInfo());
        model.addAttribute("versionList", versionList);
        model.addAttribute("imageId", lastVersion.getImageId());
        return "view";
    }


    @PostMapping("/moveToBin")
    @ResponseBody
    public String moveToBin(@RequestBody int imageId, HttpServletResponse resp) throws IOException {
        imageService.moveToBin(imageId);
        return "";
    }

    @PostMapping("/changePublicity")
    @ResponseBody
    public String changePublicity(@RequestBody int imageId) throws IOException {
        imageService.changePublicity(imageId);
        return "";
    }

    @PostMapping("/recoverFromBin")
    @ResponseBody
    public String recoverFromBin(@RequestBody int imageId, HttpServletResponse resp) throws IOException {
        // comprobar que el usuario logueado tiene acceso a la imagen
        // comprobar que idversion es valida para la imagen dada
        // retornar el json correspondiente
        // SI NO, devolver un código de error de tipo 400
        imageService.recoverFromBin(imageId);
        return "";
    }

    @GetMapping("/bin")
    public String bin(Model model, HttpServletRequest req){

        HttpSession session = req.getSession();
        int userId = (int) session.getAttribute("userId");
        List<Image> myImages = imageService.getMyImagesFromBin(userId);
        model.addAttribute("binList",myImages);
        return "bin";
    }

    @PostMapping("/removeFromBin")
    @ResponseBody
    public String removeFromBin(@RequestBody int imageId, HttpServletResponse resp) throws IOException {
        // comprobar que el usuario logueado tiene acceso a la imagen
        // comprobar que idversion es valida para la imagen dada
        // retornar el json correspondiente
        // SI NO, devolver un código de error de tipo 400
        System.out.println("Eliminando: " + imageId);
        imageService.removeFromBin(imageId);
        return "";
    }

    @GetMapping("/modify")
    public String modify(){
        return "redirect:/myImages";
    }

    //Modifica la imagen con los nuevos datos
    @PostMapping("/modify")
    public String modify(@RequestParam String jsonInfo, @RequestParam int imageId){

        versionService.addVersion(imageId, jsonInfo);
        return "view";
    }
}