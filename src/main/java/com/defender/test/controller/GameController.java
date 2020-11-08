package com.defender.test.controller;

import com.defender.test.models.Game;
import com.defender.test.models.User;
import com.defender.test.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GameController {
    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public ModelAndView getGameList(
            @AuthenticationPrincipal User user,
            Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Game> games = gameRepository.findAll();

        model.addAttribute("games", games);

        modelAndView.setViewName("games");
        return modelAndView;
    }

    @PostMapping("/games")
    public ModelAndView addGame(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String version,
            Model model) {
        ModelAndView modelAndView = new ModelAndView();

        Game newGame = new Game(name, version);

        gameRepository.save(newGame);

        Iterable<Game> games = gameRepository.findAll();

        model.addAttribute("games", games);
        modelAndView.setViewName("games");

        return modelAndView;
    }

    @PostMapping("/filter")
    public ModelAndView filter(
            @AuthenticationPrincipal User user,
            @RequestParam String filter,
            Model model) {
        ModelAndView modelAndView = new ModelAndView();

        Iterable<Game> games = gameRepository.findByName(filter);

        model.addAttribute("games", games);
        modelAndView.setViewName("games");

        return modelAndView;
    }
}
