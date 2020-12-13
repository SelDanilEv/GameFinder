package com.defender.test.repositories;

import com.defender.test.model.Faculty;
import com.defender.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IUserRepository extends JpaRepository<User, Long> {
    List<User> findByMiddleName(String MiddleName);
    User findByUsername(String UserName);
    User findByEmail(String UserName);
    List<User> findByUserCourseAndFacultyNameAndUserGroup(Integer userCourse, Faculty facultyName, Integer userGroup);
    <S extends User> S save(S s);
}