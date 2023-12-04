package com.daviesjames.drawing2.services;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.entities.User;
import com.daviesjames.drawing2.repos.ImageRepo;
import com.daviesjames.drawing2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Service
public class ImageService {
    @Autowired
    UserRepo userRepo;
    @Autowired
    ImageRepo imageRepo;

    public int save(String name, int ownerId){
        Image newImage = new Image();
        newImage.setName(name);
        newImage.setCreationDate(LocalDate.now());
        newImage.setOwnerId(ownerId);
        return imageRepo.newImage(newImage);
    }

    public List<Image> getMyImages(int userId){
        return imageRepo.getMyImages(userId);
    }

    public List<Image> getMyImagesFromBin(int userId){
        return imageRepo.getMyImagesFromBin(userId);
    }

    public Image findById(int i) {
        return imageRepo.findById(i);
    }

    public void modify(Image image, String newData) {
        System.out.println("Dentro del servicio");
        imageRepo.modifyImage(image, newData);
    }

    public String createRandomFileName(int userId) {
        User user = userRepo.findById(userId);
        int random_int = (int)Math.floor(Math.random() * (200 - 1 + 1) + 1);

        return user.getUsername() + "_" + random_int;
    }

    public void moveToBin(int imageId) {
        imageRepo.moveToBin(imageId);
    }

    public void removeFromBin(int imageId) {
        imageRepo.removeFromBin(imageId);
    }

    public void recoverFromBin(int imageId) {
        imageRepo.recoverFromBin(imageId);
    }

    public List<Image> getAllImages() {
        return imageRepo.getAllImages();
    }

    public void changePublicity(int imageId) {
        if (imageRepo.imageIsPublic(imageId)){
            imageRepo.changeToNotPublic(imageId);
        }else {
            imageRepo.changeToPublic(imageId);
        }
    }
}
