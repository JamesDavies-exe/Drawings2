package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ImageRepoImpl implements ImageRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public void saveImage(Image image) {
        jdbcTemplate.update("insert into Image (name,jsonInfo,creationDate,ownerId) values(?,?,?,?)",
                image.getName(), image.getJsonInfo(), image.getCreationDate(), image.getOwnerId());
    }

    @Override
    public List<Image> getMyImages(int userId) {
        List<Image> imageList = jdbcTemplate.query("select * from Image where ownerId=?",
                new BeanPropertyRowMapper<>(Image.class), userId);
        return imageList;
    }
}
