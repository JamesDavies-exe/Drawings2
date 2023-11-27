package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImpl  implements UserRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;

    public void saveUser(User user) {
        jdbcTemplate.update("insert into Users (fullName,username,password) values(?,?,?)", user.getFullName(),
                user.getUsername(), user.getPassword());

    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String sqlQuery = "select * from Users where username=? and password=?";

        User user = jdbcTemplate.queryForObject(sqlQuery, new BeanPropertyRowMapper<>(User.class), username, encrypt(password));
        return user;
    }

    public static String encrypt(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder encryptedPass = new StringBuilder();

            for (byte b : hash) {
                encryptedPass.append(String.format("%02x", b));
            }

            return encryptedPass.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
