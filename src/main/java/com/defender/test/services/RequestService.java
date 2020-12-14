package com.defender.test.services;

import com.defender.test.model.Championship;
import com.defender.test.model.Request;
import com.defender.test.model.User;
import com.defender.test.repositories.IRequestRepository;
import com.defender.test.services.serviceInterfaces.IRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RequestService implements IRequestService {
    private final IRequestRepository facultyRepository;

    @Autowired
    public RequestService(IRequestRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    @Override
    public List<Request> findAll() {
        log.info("RequestService : find All Request");
        return facultyRepository.findAll();
    }

    @Override
    public void addRequest(String status, String message, User user, Championship championship) {
        Request request = new Request();
        request.setMessage(message);
        request.setStatus(status);
        request.setUser(user);
        request.setChampionship(championship);
        log.info("RequestService : add Request");
        this.facultyRepository.save(request);
    }
}
