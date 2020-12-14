package com.defender.test.repositories;

import com.defender.test.model.Championship;
import com.defender.test.model.Request;
import com.defender.test.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface IRequestRepository  extends JpaRepository<Request, Integer>  {
    Request findByUserAndAndChampionship(User user, Championship championship);
    <S extends User> S save(S s);
}
