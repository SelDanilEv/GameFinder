package com.defender.test.dto;

import com.defender.test.model.Request;
import com.defender.test.repositories.IChampionshipRepository;
import com.defender.test.repositories.IRequestRepository;
import com.defender.test.repositories.IUserRepository;
import com.defender.test.services.ChampionshipService;
import com.defender.test.services.RequestService;
import com.defender.test.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class ParseStringFromFrontDto {
    private String string;

    @Autowired
    private RequestService requestService;
    @Autowired
    private UserService userService;
    @Autowired
    private ChampionshipService championshipService;

    ParseStringFromFrontDto(){
    }

    public ParseStringFromFrontDto(String string) {
        this.string = string;
    }

    public RequestDto GetRequestFromString(){
        string = string.substring(1,string.length()-1);
        String[] list = string.split("=>");
        Request request =
                requestService.findByUandC(
                        userService.findByUsername(list[0]),
                        championshipService.findByName(list[1])
                );
        return RequestDto.FromRequest(request);
    }
}
