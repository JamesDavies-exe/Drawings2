package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserRepoImpl  implements UserRepo{
    @Autowired
    JdbcTemplate jdbcTemplate;

//    List<User> userList = new ArrayList<>();

    public void saveUser(User user) {
        jdbcTemplate.update("insert into Users (fullName,username,password) values(?,?,?)", user.getFullName(),
                user.getUsername(), user.getPassword());

//        userList.add(user);
//
//        for (int i = 0; i < userList.size(); i++) {
//            User u = userList.get(i);
//            System.out.println(u.getFullName());
//        }

    }

    @Override
    public void findUserByNameAndPassord(String username, String password) {
    }
}
