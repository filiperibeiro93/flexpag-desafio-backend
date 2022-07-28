package com.flexpag.paymentscheduler.controllers;

import com.flexpag.paymentscheduler.models.UserModel;
import com.flexpag.paymentscheduler.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<UserModel> save(@RequestBody UserModel user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(user));
    }
}
