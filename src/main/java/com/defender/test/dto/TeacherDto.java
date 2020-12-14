package com.defender.test.dto;

import com.defender.test.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherDto {
    private String username;

    public static TeacherDto fromUser(User user) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setUsername(user.getUsername());
        return teacherDto;
    }
}
