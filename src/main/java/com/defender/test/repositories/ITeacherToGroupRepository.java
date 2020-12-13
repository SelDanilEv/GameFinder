package com.defender.test.repositories;

import com.defender.test.model.TeacherToGroup;
import com.defender.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITeacherToGroupRepository extends JpaRepository<TeacherToGroup, Long> {
    <S extends TeacherToGroup> S save(S s);
    List<TeacherToGroup> findByUser(User user);
}