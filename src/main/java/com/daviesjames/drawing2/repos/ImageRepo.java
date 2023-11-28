package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.Image;

import java.util.List;

public interface ImageRepo {
    void saveImage(Image image);

    List<Image> getMyImages(int userId);
}
