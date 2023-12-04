package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.Image;
import com.daviesjames.drawing2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class ImageRepoImpl implements ImageRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public int newImage(Image image) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String SQL_QUERY = "insert into Image (name,creationDate,ownerId) values(?,?,?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps =
                    connection.prepareStatement(SQL_QUERY,
                            Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, image.getName());
            ps.setString(2, image.getCreationDate().toString());
            ps.setInt(3,image.getOwnerId());
            return ps;
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    @Override
    public List<Image> getMyImages(int userId) {
        List<Image> imageList = jdbcTemplate.query("select * from Image where ownerId=? and papelera='no'",
                new BeanPropertyRowMapper<>(Image.class), userId);
        return imageList;
    }

    @Override
    public List<Image> getMyImagesFromBin(int userId) {
        List<Image> imageList = jdbcTemplate.query("select * from Image where ownerId=? and papelera='si'",
                new BeanPropertyRowMapper<>(Image.class), userId);
        return imageList;
    }

    @Override
    public Image findById(int i) {
        Image image = jdbcTemplate.queryForObject("select * from Image where id=?",
                new BeanPropertyRowMapper<>(Image.class), i);
        return image;
    }

    @Override
    public void modifyImage(Image image, String newData) {
        jdbcTemplate.update("UPDATE Version SET jsonInfo = ? WHERE ImageId = ?",
               newData, image.getId());
    }

    @Override
    public void moveToBin(int imageId) {
        jdbcTemplate.update("update Image set papelera = 'si' where id=?", imageId);
    }

    @Override
    public void removeFromBin(int imageId) {
        jdbcTemplate.update("DELETE FROM Image WHERE papelera = 'si' and id=?", imageId);
    }

    @Override
    public void recoverFromBin(int imageId) {
        jdbcTemplate.update("update Image set papelera = 'no' where id = ?", imageId);
    }

    @Override
    public List<Image> getAllImages() {
        return jdbcTemplate.query("select * from Image", new BeanPropertyRowMapper<>(Image.class));
    }

    @Override
    public boolean imageIsPublic(int imageId) {
        Image image = jdbcTemplate.queryForObject("select * from Image where id = ?",
                new BeanPropertyRowMapper<>(Image.class),imageId);
        if (image.getPublicity() == 1){
            System.out.println("La imagen " + imageId + " es publica");
            return true;
        }
        System.out.println("La imagen " + imageId + " no es publica");
        return false;
    }

    @Override
    public void changeToNotPublic(int imageId) {
        jdbcTemplate.update("update Image set public = 0 where id = ?", imageId);
    }

    @Override
    public void changeToPublic(int imageId) {
        jdbcTemplate.update("update Image set public = 1 where id = ?", imageId);
    }
}
