package com.defender.test.services.serviceInterfaces;


import com.defender.test.dto.TeacherToGroupDto;
import com.defender.test.model.User;

import java.util.List;

public interface ITeacherToGroupService {
    void addRecord(TeacherToGroupDto teacherToGroupDto);
//    List<User> getStudents(String facultyName, String subjectName, Integer groupValue, Integer courseValue, String username);
}
