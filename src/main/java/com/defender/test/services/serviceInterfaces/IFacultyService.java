package com.defender.test.services.serviceInterfaces;

import com.defender.test.model.Faculty;

import java.util.List;

public interface IFacultyService {
    List<Faculty> findAll();
    void addFaculty(String facultyName);
}
