package com.defender.test.services.serviceInterfaces;


import com.defender.test.dto.RateDto;

import java.util.List;

public interface IPerfomanceService {
    void rate(RateDto rateDto);
    List<String> findByUser(String username);
}
