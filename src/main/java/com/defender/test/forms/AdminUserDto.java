package com.defender.test.forms;

import com.defender.test.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDto {
    private Integer id;
    private String username;
    private boolean active;

    public User toUser(){
        User user = new User();
        user.setIdUser(id);
        user.setUsername(username);
        user.setActive(active);
        return user;
    }

    public static AdminUserDto fromUser(User user) {
        AdminUserDto userDto = new AdminUserDto();
        userDto.setId(user.getIdUser());
        userDto.setUsername(user.getUsername());
        userDto.setActive(user.isActive());
        return userDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
