package com.defender.test.repositories;

import com.defender.test.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ISubjectRepository extends JpaRepository<Subject, Integer> {
    List<Subject> findBySubject(String subject);
    <S extends Subject> S save(S s);
}
