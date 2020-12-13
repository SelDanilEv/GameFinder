package com.defender.test.services.serviceInterfaces;

import com.defender.test.model.Subject;

import java.util.List;

public interface ISubjectService {
    List<Subject> findAll();
    void deleteSubjectByName(String subject);
    void addSubject(String subject);
}