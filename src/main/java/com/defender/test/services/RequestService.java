package com.defender.test.services;

import com.defender.test.model.Championship;
import com.defender.test.model.Request;
import com.defender.test.model.User;
import com.defender.test.repositories.IRequestRepository;
import com.defender.test.services.serviceInterfaces.IMyRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RequestService implements IMyRequestService {
    private final IRequestRepository requestRepository;

    @Autowired
    public RequestService(IRequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    public List<Request> findAll() {
        var list = requestRepository.findAll();
        log.info("RequestService : find All Request");
        return list;
    }

    public void addRequest(String status, String message, User user, Championship championship) {
        Request request = new Request();
        request.setMessage(message);
        request.setStatus(status);
        request.setUser(user);
        request.setChampionship(championship);
        log.info("RequestService : add Request");
        this.requestRepository.save(request);
    }

    public Request findByUandC(User user, Championship championship) {
        return requestRepository.findByUserAndAndChampionship(user, championship);
    }


}
