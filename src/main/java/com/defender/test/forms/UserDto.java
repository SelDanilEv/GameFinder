package com.defender.test.forms;

import com.defender.test.models.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private Integer id;
    private String username;

    public User toUser(){
        User user = new User();
        user.setIdUser(id);
        user.setUsername(username);
        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getIdUser());
        userDto.setUsername(user.getUsername());

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
}
