package com.defender.test.repositories;

import com.defender.test.model.Championship;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IChampionshipRepository extends JpaRepository<Championship, Integer> {
    List<Championship> findByName(String name);
    <S extends Championship> S save(S s);
}
