package com.defender.test.controller.rest;

import com.defender.test.forms.UserDto;
import com.defender.test.models.Game;
import com.defender.test.models.User;
import com.defender.test.repository.GameRepository;
import com.defender.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Enumeration;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @GetMapping("/games")
    public ModelAndView getGameList(
            Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Iterable<Game> games = gameRepository.findAll();

        model.addAttribute("games", games);

        modelAndView.setViewName("games");
        return modelAndView;
    }

//    @PostMapping("/games")
//    public ModelAndView addGame(
//            @RequestParam String name,
//            @RequestParam String version,
//            Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        Game newGame = new Game(name, version);
//
//        gameRepository.save(newGame);
//
//        Iterable<Game> games = gameRepository.findAll();
//
//        model.addAttribute("games", games);
//        modelAndView.setViewName("games");
//
//        return modelAndView;
//    }
//
//    @PostMapping("/filter")
//    public ModelAndView filter(
//            @RequestParam String filter,
//            Model model) {
//        ModelAndView modelAndView = new ModelAndView();
//
//        Iterable<Game> games = gameRepository.findByName(filter);
//
//        model.addAttribute("games", games);
//        modelAndView.setViewName("games");
//
//        return modelAndView;
//    }

    @GetMapping(value = "{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id) {
        User user = userRepository.findById(id).get();

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        UserDto result = UserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
