package com.daviesjames.drawing2.services;

import com.daviesjames.drawing2.Exceptions.InvalidPassword;
import com.daviesjames.drawing2.Exceptions.NoArguments;
import com.daviesjames.drawing2.Exceptions.UserIncorrect;
import com.daviesjames.drawing2.entities.User;
import com.daviesjames.drawing2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    public void saveUser(String fullName, String username, String password) throws NoArguments, InvalidPassword {
        if(fullName.length() == 0 || username.length() == 0 || password.length() == 0 ){
            throw new NoArguments("Debes introducir todos los campos");
        }

        if (password.length() < 5){
            throw new InvalidPassword("La constraseña debe tener al menos 5 caracteres");
        }
        User user = new User();
        user.setFullName(fullName);
        user.setUsername(username);
        user.setPassword(password);
        userRepo.saveUser(user);
    }

    public User checkUser(String username, String password) throws UserIncorrect {
        User user = userRepo.findByUsernameAndPassword(username, password);
        if (user == null){
            throw new UserIncorrect("El usuario o la contraseña son incorrectos");
        }
        return user;
    }


}
