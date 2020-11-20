package com.defender.test.repository;


import com.defender.test.models.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends CrudRepository<Game, Integer> {
    Game findByName(String name);
    List<Game> findAllByName(String name);
}
