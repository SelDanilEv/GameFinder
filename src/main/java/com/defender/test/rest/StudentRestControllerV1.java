package com.defender.test.rest;

import com.defender.test.dto.StudentDto;
import com.defender.test.model.*;
import com.defender.test.repositories.ISubjectRepository;
import com.defender.test.repositories.IUserRepository;
import com.defender.test.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/students/")
public class StudentRestControllerV1 {
    private final UserService userService;
    private final ISubjectRepository iSubjectRepository;
    private final IUserRepository iUserRepository;
    @Autowired
    public StudentRestControllerV1(UserService userService, ISubjectRepository iSubjectRepository, IUserRepository iUserRepository) {
        this.userService = userService;
        this.iSubjectRepository = iSubjectRepository;
        this.iUserRepository = iUserRepository;
    }

    @GetMapping(value = "{username}")
    public ResponseEntity<StudentDto> getStudentByUsername(@PathVariable(name = "username") String username){
        User user = userService.findByUsername(username);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        StudentDto result = StudentDto.fromUser(user);
        log.info("Get request : /api/v1/auth/username");
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
