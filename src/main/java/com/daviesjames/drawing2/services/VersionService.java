package com.daviesjames.drawing2.services;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.entities.Version;
import com.daviesjames.drawing2.repos.VersionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VersionService {
    @Autowired
    VersionRepo versionRepo;

   public void addVersion(int imageId, String jsonInfo){
        Version version = new Version();
        version.setImageId(imageId);
        version.setJsonInfo(jsonInfo);
        versionRepo.newVersion(version);
    }

    public Version getByImageId(int imageId) {
       return versionRepo.getByImageId(imageId);

    }

    public List<Version> getAllVersionsByImageId(String imageId) {
       return versionRepo.getAllVersionsByImageId(imageId);
    }
}
