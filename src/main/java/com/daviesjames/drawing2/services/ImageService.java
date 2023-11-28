package com.daviesjames.drawing2.services;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.repos.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    ImageRepo imageRepo;

    public void save(String name, String jsonInfo, int ownerId){
        Image newImage = new Image();
        newImage.setName(name);
        newImage.setJsonInfo(jsonInfo);
        newImage.setOwnerId(ownerId);
        newImage.setCreationDate(LocalDate.now());
        imageRepo.saveImage(newImage);
    }

    public List<Image> getMyImages(int userId){
        return imageRepo.getMyImages(userId);
    }
}
