package com.defender.test.service;

import com.defender.test.forms.RatingDto;
import com.defender.test.models.Rating;
import com.defender.test.repository.GameRepository;
import com.defender.test.repository.RatingRepository;
import com.defender.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public RatingService() {
    }

    public Rating FromDto(RatingDto form) {
        Rating rating = new Rating();
        if(form == null){
            return rating;
        }
        if (form.getId() != null) {
            rating.setId(form.getId());
        }
        if (form.getUser() != "" && form.getUser() != null) {
            rating.setUser(Collections.singleton(userRepository.findByUsername(form.getUser())));
        }
        if (form.getGame() != "" && form.getGame() != null) {
            rating.setGame(Collections.singleton(gameRepository.findByName(form.getUser())));
        }
        if (form.getValue() != null) {
            rating.setValue(form.getValue());
        }
        return rating;
    }

    public Rating AddNewRating(Rating rating) {
        Rating ratingFromDB = ratingRepository.findById(rating.getId()).get();
        if (ratingFromDB != null) {
            return null;
        }
        Rating newRating = new Rating();
        newRating.setUser(rating.getUser());
        newRating.setGame(rating.getGame());
        newRating.setValue(rating.getValue());
        ratingRepository.save(newRating);
        return newRating;
    }

    public Rating EditRating(Rating rating) {
        Rating ratingFromDB = ratingRepository.findById(rating.getId()).get();
        if (ratingFromDB == null) {
            return null;
        }
        ratingRepository.save(rating);
        return rating;
    }

    public void DeleteRating(Rating rating) {
        Integer ratingId = ratingRepository.findById(rating.getId()).get().getId();
        if (ratingId != null) {
            ratingRepository.deleteById(ratingId);
        }
    }
}
