package com.example.task3;

import java.io.Serializable;

public class DataFormContainer implements Serializable {
    private String avatarHref;
    private String name;
    private String phone;
    private String email;

    public DataFormContainer(String image, String name, String phone, String email) {
        this.avatarHref = image;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatarHref() {
        return avatarHref;
    }

    public void setAvatarHref(String avatarHref) {
        this.avatarHref = avatarHref;
    }
}
