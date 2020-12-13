package com.defender.test.repositories;

import com.defender.test.model.AcademicPerformance;
import com.defender.test.model.Subject;
import com.defender.test.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPerfomanceRepository  extends JpaRepository<AcademicPerformance, Integer>  {
    <S extends AcademicPerformance> S save(S s);
    List<AcademicPerformance> findByUser(User user);
    Page<AcademicPerformance> findByUserAndSubject(User user, Subject subject, Pageable pageable);
    Page<AcademicPerformance> findByUser(User user, Pageable pageable);
}
