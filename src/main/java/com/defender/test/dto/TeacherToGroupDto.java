package com.defender.test.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TeacherToGroupDto {
    private String faculty;
    private Integer course;
    private Integer tgroup;
    private String user;
    private String subject;


    public TeacherToGroupDto(String faculty, Integer course, Integer tgroup, String user, String subject) {
        this.faculty = faculty;
        this.course = course;
        this.tgroup = tgroup;
        this.user = user;
        this.subject = subject;
    }
}