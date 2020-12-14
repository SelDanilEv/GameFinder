package com.defender.test.rest;

import com.defender.test.dto.AdminUserDto;
import com.defender.test.model.Faculty;
import com.defender.test.model.Championship;
import com.defender.test.model.User;
import com.defender.test.services.FacultyService;
import com.defender.test.services.ChampionshipService;
import com.defender.test.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final ChampionshipService subjectService;
    private final FacultyService facultyService;

    @Autowired
    public AdminRestControllerV1(UserService userService, ChampionshipService subjectService, FacultyService facultyService) {
        this.userService = userService;
        this.subjectService = subjectService;
        this.facultyService = facultyService;
    }

    @GetMapping(value = "users/{username}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "username") String username) {
        User user;
        user = userService.findByUsername(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);
        log.info("Get request : /api/v1/admin/users");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("addSubject")
    public ResponseEntity addSubject(RequestEntity<Championship> subject) {
        subjectService.addChampionship(Objects.requireNonNull(subject.getBody()).getName());
        log.info("Get request : /api/v1/admin/addSubject");
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getName(), HttpStatus.CREATED);
    }

    @PostMapping("addFaculty")
    public ResponseEntity addFaculty(RequestEntity<Faculty> faculty) {
        facultyService.addFaculty(Objects.requireNonNull(faculty.getBody()).getFaculty());
        log.info("Get request : /api/v1/admin/addFaculty");
        return new ResponseEntity<>(Objects.requireNonNull(faculty.getBody()).getFaculty(), HttpStatus.CREATED);
    }

    @PutMapping("deActivateSubject")
    public ResponseEntity deActivateSubject(RequestEntity<Championship> subject) {
        subjectService.deleteChampionshipByName(Objects.requireNonNull(subject.getBody()).getName());
        log.info("Get request : /api/v1/admin/deActivateSubject");
        return new ResponseEntity<>(Objects.requireNonNull(subject.getBody()).getName(), HttpStatus.CREATED);
    }
}
