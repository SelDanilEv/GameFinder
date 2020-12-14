package com.defender.test.services.serviceInterfaces;

import com.defender.test.model.Championship;
import com.defender.test.model.Request;
import com.defender.test.model.User;

import java.util.List;

public interface IMyRequestService {
    void addRequest(String status, String message, User user, Championship championship);
}
