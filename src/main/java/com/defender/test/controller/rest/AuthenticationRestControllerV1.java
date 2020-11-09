package com.defender.test.controller.rest;

import com.defender.test.forms.AuthenticationRequestDto;
import com.defender.test.models.Role;
import com.defender.test.models.User;
import com.defender.test.repository.UserRepository;
import com.defender.test.security.JwtTokenProvider;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Collections;


@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @GetMapping("/login")
    public ModelAndView showLoginPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("login");
        AuthenticationRequestDto form = new AuthenticationRequestDto();
        model.addAttribute("form", form);
        return modelAndView;
    }

    @GetMapping("/registration")
    public ModelAndView showRegistrationPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("registration");
        AuthenticationRequestDto form = new AuthenticationRequestDto();
        model.addAttribute("form", form);
        return modelAndView;
    }

    @PostMapping("/login")
    public ModelAndView login(Model model,@ModelAttribute("form") AuthenticationRequestDto form) {
        try {
            String username = form.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, form.getPassword()));
            User user = userRepository.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = "Bearer_"+jwtTokenProvider.createToken(username, new ArrayList<>(user.getRoles()));

            ModelAndView response = new ModelAndView();
            response.addObject("username", username);
            response.addObject("token", token);
            response.setViewName("home");

            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("/registrate")
    public ModelAndView registrate(Model model,@ModelAttribute("form") AuthenticationRequestDto form) {
        try {
            ModelAndView response = new ModelAndView();

            String userName = form.getUsername();
            String password = form.getPassword();


            if (registrate(new User(userName, password, false, null)) == null) {
                response.addObject("errorMessage", "ERROR USER IS ALREADY EXIST");
                response.setViewName("registration");
                return response;
            }
            response.setViewName("login");
            return response;
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    public User registrate (User user){
        User userFromDB = (User)userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return null;
        }
        User newUser = new User();
        newUser.setUsername(user.getUsername());
        newUser.setPassword(user.getPassword());
        newUser.setActive(false);
        newUser.setRoles(Collections.singleton(Role.USER));
        userRepository.save(newUser);
        return newUser;
    }
}
