package com.curso.springboot.primero.springboot_primero.entities.DTO;

import com.curso.springboot.primero.springboot_primero.entities.User;

public class UserDTO {

    private String title;
    private User user;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    
}
