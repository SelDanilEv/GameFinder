package com.defender.test.rest;

import com.defender.test.dto.PlayerDto;
import com.defender.test.model.*;
import com.defender.test.repositories.IChampionshipRepository;
import com.defender.test.repositories.IUserRepository;
import com.defender.test.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/students/")
public class PlayerRestControllerV1 {
    private final UserService userService;
    private final IChampionshipRepository iChampionshipRepository;
    private final IUserRepository iUserRepository;
    @Autowired
    public PlayerRestControllerV1(UserService userService, IChampionshipRepository iChampionshipRepository, IUserRepository iUserRepository) {
        this.userService = userService;
        this.iChampionshipRepository = iChampionshipRepository;
        this.iUserRepository = iUserRepository;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<PlayerDto> getPlayerByUsername(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        PlayerDto result = PlayerDto.fromUser(user);
        log.info("Get request : /api/v1/auth/username");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
