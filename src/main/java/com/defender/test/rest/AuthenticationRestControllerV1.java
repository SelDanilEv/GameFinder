package com.defender.test.rest;

import com.defender.test.Validator.PlayerValidator;
import com.defender.test.dto.AuthenticationRequestDto;
import com.defender.test.model.*;
import com.defender.test.security.jwt.JwtTokenProvider;
import com.defender.test.services.FacultyService;
import com.defender.test.services.ChampionshipService;
import com.defender.test.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/auth")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final FacultyService facultyService;
    private final ChampionshipService subjectService;
    private final PlayerValidator studentValidator;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, FacultyService facultyService, ChampionshipService subjectService, PlayerValidator studentValidator) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.facultyService = facultyService;
        this.subjectService = subjectService;
        this.studentValidator = studentValidator;
    }
    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ResponseEntity<User> Register(@RequestBody AuthenticationRequestDto requestDto) throws MethodArgumentNotValidException {

        try {
            String username = requestDto.getUsername();
            User user = userService.findByUsername(username);
            if (user != null) {
                throw new UsernameNotFoundException("User with username: " + username + " already exist");
            }

            user = new User(requestDto.getUsername(),requestDto.getPassword());

            userService.register(user);

            Map<Object, Object> response = new HashMap<>();

            log.info("Get request : /api/v1/auth/registration");
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        } catch (AuthenticationException e) {
            log.info("Get request : /api/v1/auth/registration ---- Invalid username");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);
            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            log.info("Get request : /api/v1/auth/login");
            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            log.info("Get request : /api/v1/auth/login ---- Invalid username or password");
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @GetMapping(value = {"/faculties"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Faculty>> facultyList() {
        log.info("Get request : /api/v1/auth/faculties");
        return new ResponseEntity<>(facultyService.findAll(), HttpStatus.OK);
    }

    @GetMapping(value = {"/subjects"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Championship>> subjectList() {
        return new ResponseEntity<>(subjectService.findAll()
                                                    .stream()
                                                    .filter(i -> i.getStatus() != Status.DELETED)
                                                    .collect(Collectors.toList()), HttpStatus.OK);
    }

    @GetMapping(value = {"/userinfo"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity GetUsername(HttpServletRequest request) {
        String token = jwtTokenProvider.resolveToken(request);
        String username = jwtTokenProvider.getUsername(token);
        User user = userService.findByUsername(username);
        Role role = user.getRoles().get(0);
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("role", role.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
