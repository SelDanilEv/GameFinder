package com.defender.test.repository;


import com.defender.test.models.Championship;
import com.defender.test.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChampionshipRepository extends CrudRepository<Championship, Integer> {
    Championship findByName(String name);
    List<Championship> findAllByName(String name);
}
