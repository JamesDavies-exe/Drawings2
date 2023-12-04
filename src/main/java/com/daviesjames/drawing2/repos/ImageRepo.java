package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.Image;

import java.util.List;

public interface ImageRepo {
    int newImage(Image image);

    List<Image> getMyImages(int userId);

    List<Image> getMyImagesFromBin(int userId);

    Image findById(int i);

    void modifyImage(Image image, String newData);

    void moveToBin(int imageId);

    void removeFromBin(int imageId);

    void recoverFromBin(int imageId);

    List<Image> getAllImages();

    boolean imageIsPublic(int imageId);

    void changeToNotPublic(int imageId);

    void changeToPublic(int imageId);
}
