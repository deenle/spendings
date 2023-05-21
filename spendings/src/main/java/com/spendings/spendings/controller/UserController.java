package com.spendings.spendings.controller;

import com.spendings.spendings.dto.UserDTO;
import com.spendings.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDTO> getAllUsers() {
        log.debug("getAllUsers working from {}", UserController.class.getSimpleName());
        return userService.findAll().stream().map(
                        user -> UserDTO.builder()
                                .name(user.getName())
                                .build())
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public UserDTO getOneUserById(@PathVariable("id") int id) {
        log.debug("getUserById working for id: {} from {}", id, UserController.class.getSimpleName());
//        return userService.findOne(id);
        return UserDTO.builder()
                .name(userService.findOne(id).getName())
                .build();
    }

}
