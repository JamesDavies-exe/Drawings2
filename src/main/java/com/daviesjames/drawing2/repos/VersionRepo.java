package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.Version;

import java.util.List;

public interface VersionRepo {
    void newVersion(Version version);

    Version getByImageId(int imageId);

    List<Version> getAllVersionsByImageId(String imageId);

}
