package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VersionRepoImpl implements VersionRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void newVersion(Version version) {
        jdbcTemplate.update("insert into Version (imageId, jsonInfo) values (?,?)",
                version.getImageId(), version.getJsonInfo());
    }

    @Override
    public Version getByImageId(int imageId) {
        Version version = jdbcTemplate.queryForObject("select * from Version where imageId=? order by id desc limit 1",
                new BeanPropertyRowMapper<>(Version.class), imageId);
        return version;
    }

    @Override
    public List<Version> getAllVersionsByImageId(String imageId) {
        List<Version> versionList = jdbcTemplate.query("select * from Version where imageId=?",
                new BeanPropertyRowMapper<>(Version.class), imageId);
        return versionList;
    }
}
