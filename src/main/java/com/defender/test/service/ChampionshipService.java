package com.defender.test.service;

import com.defender.test.Exceptions.ChampionshipNotFoundException;
import com.defender.test.forms.ChampionshipDto;
import com.defender.test.models.Championship;
import com.defender.test.repository.ChampionshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChampionshipService {
    @Autowired
    private ChampionshipRepository championshipRepository;

    @Autowired
    public ChampionshipService() {
    }

    public Championship loadChampionshipByChampionshipname(String name) throws ChampionshipNotFoundException {
        Championship championship = championshipRepository.findByName(name);

        if (championship == null) {
            throw new ChampionshipNotFoundException("Championship with name: " + name + " not found");
        }

        return championship;
    }

    public Championship FromDto(ChampionshipDto form) {
        Championship championship = new Championship();
        if(form ==null){
            return championship;
        }
        if (form.getId() != null) {
            championship.setId(form.getId());
        }
        if (form.getName() != "" && form.getName() != null) {
            championship.setName(form.getName());
        }
        if (form.getPrize() != null) {
            championship.setPrize(form.getPrize());
        }
        return championship;
    }

    public Championship AddNewChampionship(Championship championship) {
        Championship championshipFromDB = championshipRepository.findByName(championship.getName());
        if (championshipFromDB != null) {
            return null;
        }
        Championship newChampionship = new Championship();
        newChampionship.setName(championship.getName());
        newChampionship.setPrize(championship.getPrize());
        championshipRepository.save(newChampionship);
        return newChampionship;
    }

    public Championship EditChampionship(Championship championship) {
        Championship championshipFromDB = championshipRepository.findById(championship.getId()).get();
        if (championshipFromDB == null) {
            return null;
        }
        championshipRepository.save(championship);
        return championship;
    }

    public void DeleteChampionship(Championship championship) {
        Integer championshipId = championshipRepository.findById(championship.getId()).get().getId();
        if (championshipId != null) {
            championshipRepository.deleteById(championshipId);
        }
    }
}
