package com.defender.test.services.serviceInterfaces;

import com.defender.test.model.Championship;

import java.util.List;

public interface IChampionshipService {
    List<Championship> findAll();
    void deleteChampionshipByName(String name);
    void addChampionship(String name);
}