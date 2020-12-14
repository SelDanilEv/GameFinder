package com.defender.test.dto;

import com.defender.test.model.Faculty;
import com.defender.test.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDto {
    private String username;
    private String email;
    private boolean nothing;

    public User toUser(){
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        return user;
    }

    public static StudentDto fromUser(User user) {
        StudentDto studentDto = new StudentDto();
        studentDto.setUsername(user.getUsername());
        studentDto.setEmail(user.getEmail());
        return studentDto;
    }
}
