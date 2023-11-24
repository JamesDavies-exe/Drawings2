package com.daviesjames.drawing2.entities;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {
    String fullName;
    String username;
    String password;

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = encrypt(password);
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
