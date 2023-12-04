package com.daviesjames.drawing2.entities;

import java.time.LocalDate;

public class Image {
    int id;
    String name;
    LocalDate creationDate;
    int ownerId;
    int publicity;
    String papelera;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int userId) {
        this.ownerId = userId;
    }

    public int getPublicity() {
        return publicity;
    }


    public String getPapelera() {
        return papelera;
    }

    public void setPapelera(String papelera) {
        this.papelera = papelera;
    }
}
