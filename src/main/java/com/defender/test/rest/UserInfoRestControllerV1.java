package com.defender.test.rest;

import com.defender.test.Validator.StudentValidator;
import com.defender.test.Validator.TeacherValidator;
import com.defender.test.dto.AdminUserDto;
import com.defender.test.dto.StudentDto;
import com.defender.test.dto.TeacherDto;
import com.defender.test.model.Role;
import com.defender.test.model.User;
import com.defender.test.security.jwt.JwtTokenProvider;
import com.defender.test.services.FacultyService;
import com.defender.test.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/userinfo")
public class UserInfoRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final FacultyService facultyService;
    private final StudentValidator studentValidator;
    private final TeacherValidator teacherValidator;

    @Autowired
    public UserInfoRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, FacultyService facultyService, StudentValidator studentValidator, TeacherValidator teacherValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.facultyService = facultyService;
        this.studentValidator = studentValidator;
        this.teacherValidator = teacherValidator;
    }

    @GetMapping(value = {"/"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        if(role.getName().equals("ROLE_STUDENT")) {
            StudentDto studentDto = StudentDto.fromUser(user);
            log.info("Get request : /api/v1/userinfo/ -- ROLE_STUDENT");
            return new ResponseEntity<>(studentDto, HttpStatus.OK);
        }else if(role.getName().equals("ROLE_TEACHER")){
            TeacherDto teacherDto = TeacherDto.fromUser(user);
            log.info("Get request : /api/v1/userinfo/ -- ROLE_TEACHER");
            return new ResponseEntity<>(teacherDto, HttpStatus.OK);
        }else{
            AdminUserDto adminUserDto = AdminUserDto.fromUser(user);
            log.info("Get request : /api/v1/userinfo/ -- ADMIN");
            return new ResponseEntity<>(adminUserDto, HttpStatus.OK);
        }
    }
}
