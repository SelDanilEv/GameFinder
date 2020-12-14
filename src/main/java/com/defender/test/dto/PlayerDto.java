package com.defender.test.dto;

import com.defender.test.model.Faculty;
import com.defender.test.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerDto {
    private String username;
    private String email;
    private boolean nothing;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }

    public static PlayerDto fromUser(User user) {
        PlayerDto studentDto = new PlayerDto();
        studentDto.setUsername(user.getUsername());
        studentDto.setEmail(user.getEmail());
        return studentDto;
    }
}