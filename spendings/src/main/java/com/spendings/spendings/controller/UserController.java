package com.spendings.spendings.controller;

import com.spendings.spendings.model.User;
import com.spendings.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;
    final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping()
    public List<User> getAllUsers() {
        logger.debug("getAllUsers working from {}", UserController.class.getSimpleName());
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getOneUserById(@PathVariable("id") int id) {
        logger.debug("getUserById working for id: {} from {}", id, UserController.class.getSimpleName());
        return userService.findOne(id);
    }
}
