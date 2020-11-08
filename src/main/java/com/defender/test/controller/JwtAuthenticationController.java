package com.defender.test.controller;

import com.defender.test.config.JwtTokenUtil;
import com.defender.test.forms.UserForm;
import com.defender.test.models.User;
import com.defender.test.repository.UserRepository;
import com.defender.test.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(Model model) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @PostMapping("/authenticate")
    public ModelAndView CheckUser(Model model,
                                   @ModelAttribute("userForm") UserForm userForm) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        String userName = userForm.getUsername();
        String password = userForm.getPassword();

        User userFromDB = userRepository.findByUsername(userName);
        if (userFromDB == null) {
            model.addAttribute("errorMessage", "ERROR USER IS NOT EXIST");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        if (!password.equals(userFromDB.getPassword())) {
            model.addAttribute("errorMessage", "WRONG PASSWORD");
            modelAndView.setViewName("login");
            return modelAndView;
        }
        modelAndView.setViewName("home");

        authenticate(userFromDB);

        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(userName);

        final String token = jwtTokenUtil.generateToken(userDetails);

        return modelAndView;
    }


    private void authenticate(User user) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getUsername(),
                            user.getPassword(),
                            user.getRoles()));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}