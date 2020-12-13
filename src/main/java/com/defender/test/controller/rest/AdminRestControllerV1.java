package com.defender.test.controller.rest;

import com.defender.test.forms.*;
import com.defender.test.models.Championship;
import com.defender.test.models.Game;
import com.defender.test.repository.ChampionshipRepository;
import com.defender.test.repository.GameRepository;
import com.defender.test.service.ChampionshipService;
import com.defender.test.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "/api/v1/admin/")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminRestControllerV1 {
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GameService gameService;

    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    private ChampionshipService championshipService;

    @Autowired
    public AdminRestControllerV1() {
    }

//    @GetMapping(value = "{id}")
//    public ResponseEntity<UserDto> getUserById(@PathVariable(name = "id") Integer id) {
//        User user = userRepository.findById(id).get();
//
//        if (user == null) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        AdminUserDto result = AdminUserDto.fromUser(user);
//
//        return new ResponseEntity(result, HttpStatus.OK);
//    }

    @GetMapping("/home")
    public ModelAndView getAdminPage(Model model) {
        model.addAttribute("token", model.getAttribute("token"));

        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);

        Iterable<Championship> championships = championshipRepository.findAll();
        model.addAttribute("championships", championships);

        return toAdminPage(model);
    }

    @PostMapping("/AddGame")
    public ModelAndView addGame(
            @ModelAttribute("GameForm") GameDto form,
            BindingResult bindingResult,
            Model model) {
        gameService.AddNewGame(gameService.FromDto(form));

        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);

        return toAdminPage(model);
    }

    @PostMapping("/ChangeGame")
    public ModelAndView changeGame(
            @ModelAttribute("GameForm") GameDto form,
            BindingResult bindingResult,
            Model model) {
        gameService.EditGame(gameService.FromDto(form));

        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);

        return toAdminPage(model);
    }

    @PostMapping("/DeleteGame")
    public ModelAndView deleteGame(
            @ModelAttribute("GameForm") GameDto form,
            BindingResult bindingResult,
            Model model) {
        gameService.DeleteGame(gameService.FromDto(form));

        Iterable<Game> games = gameRepository.findAll();
        model.addAttribute("games", games);

        return toAdminPage(model);
    }

    @PostMapping("/GameFilter")
    public ModelAndView GameFilter(
            @RequestParam String filter,
            Model model) {
        Iterable<Game> games = null;
        if(filter!="") {
            games = gameRepository.findAllByName(filter);
        }
        else{
            games = gameRepository.findAll();
        }
        model.addAttribute("games", games);
        return toAdminPage(model);
    }
    //------------------------
    @PostMapping("/AddChampionship")
    public ModelAndView addChampionship(
            @ModelAttribute("ChampionshipForm") ChampionshipDto form,
            BindingResult bindingResult,
            Model model) {
        championshipService.AddNewChampionship(championshipService.FromDto(form));

        Iterable<Championship> championships = championshipRepository.findAll();
        model.addAttribute("championships", championships);

        return toAdminPage(model);
    }

    @PostMapping("/ChangeChampionship")
    public ModelAndView changeChampionship(
            @ModelAttribute("ChampionshipForm") ChampionshipDto form,
            BindingResult bindingResult,
            Model model) {
        championshipService.EditChampionship(championshipService.FromDto(form));

        Iterable<Championship> championships = championshipRepository.findAll();
        model.addAttribute("championships", championships);

        return toAdminPage(model);
    }

    @PostMapping("/DeleteChampionship")
    public ModelAndView deleteChampionship(
            @ModelAttribute("ChampionshipForm") ChampionshipDto form,
            BindingResult bindingResult,
            Model model) {
        championshipService.DeleteChampionship(championshipService.FromDto(form));

        Iterable<Championship> championships = championshipRepository.findAll();
        model.addAttribute("championships", championships);

        return toAdminPage(model);
    }

    @PostMapping("/ChampionshipFilter")
    public ModelAndView ChampionshipFilter(
            @RequestParam String filter,
            Model model) {
        Iterable<Championship> championships = null;
        if(filter!="") {
            championships = championshipRepository.findAllByName(filter);
        }
        else{
            championships = championshipRepository.findAll();
        }
        model.addAttribute("championships", championships);
        return toAdminPage(model);
    }
    //------------------------



    public ModelAndView toAdminPage(Model model){
        ChampionshipDto champForm = new ChampionshipDto();
        model.addAttribute("ChampionshipForm", champForm);

        GameDto gameForm = new GameDto();
        model.addAttribute("GameForm", gameForm);
        
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminpage");

        return modelAndView;
    }
}
