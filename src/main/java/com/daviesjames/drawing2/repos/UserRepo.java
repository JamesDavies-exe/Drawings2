package com.daviesjames.drawing2.repos;

import com.daviesjames.drawing2.entities.User;

public interface UserRepo {
    public void saveUser(User user);


    User findByUsernameAndPassword(String username, String password);

    boolean findById(int id);
}
