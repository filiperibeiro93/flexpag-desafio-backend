package com.flexpag.paymentscheduler.services;

import com.flexpag.paymentscheduler.models.UserModel;
import com.flexpag.paymentscheduler.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public UserModel save(UserModel userModel) {
        return userRepository.save(userModel);
    }
}
