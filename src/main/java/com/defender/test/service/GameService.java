package com.defender.test.service;

import com.defender.test.Exceptions.GameNotFoundException;
import com.defender.test.forms.GameDto;
import com.defender.test.models.Game;
import com.defender.test.models.Role;
import com.defender.test.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;

    @Autowired
    public GameService() {
    }

    public Game loadGameByGamename(String name) throws GameNotFoundException {
        Game game = gameRepository.findByName(name);

        if (game == null) {
            throw new GameNotFoundException("Game with name: " + name + " not found");
        }

        return game;
    }

    public Game FromDto(GameDto form) {
        Game game = new Game();
        if(form ==null){
            return game;
        }
        if (form.getId() != null) {
            game.setId(form.getId());
        }
        if (form.getName() != "" && form.getName() != null) {
            game.setName(form.getName());
        }
        if (form.getVersion() != "" && form.getVersion() != null) {
            game.setVersion(form.getVersion());
        }
        return game;
    }

    public Game AddNewGame(Game game) {
        Game gameFromDB = gameRepository.findByName(game.getName());
        if (gameFromDB != null) {
            return null;
        }
        Game newGame = new Game();
        newGame.setName(game.getName());
        newGame.setVersion(game.getVersion());
        gameRepository.save(newGame);
        return newGame;
    }

    public Game EditGame(Game game) {
        Game gameFromDB = gameRepository.findById(game.getId()).get();
        if (gameFromDB == null) {
            return null;
        }
        gameRepository.save(game);
        return game;
    }

    public void DeleteGame(Game game) {
        Integer gameId = gameRepository.findById(game.getId()).get().getId();
        if (gameId != null) {
            gameRepository.deleteById(gameId);
        }
    }
}
