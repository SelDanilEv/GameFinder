package com.defender.test.repositories;

import com.defender.test.model.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFacultyRepository  extends JpaRepository<Faculty, Integer>  {
    Faculty findByFaculty(String faculty);
}
