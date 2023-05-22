package com.spendings.spendings.controller;

import com.spendings.spendings.dto.SpendingDTO;
import com.spendings.spendings.dto.UserDTO;
import com.spendings.spendings.model.User;
import com.spendings.spendings.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
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

    @GetMapping("/spendinglistbyuser")
    public List<SpendingDTO> getAllSpendingsByUserId(@RequestHeader("USER-ID") int id) {
        log.debug("getAllSpendingsByUserId working for id: {} from {}", id, UserController.class.getSimpleName());
        User user = userService.findOne(id);
        List<SpendingDTO> spendingDTOS = user.getSpendings().stream().map(spending -> SpendingDTO.builder()
                        .amount(spending.getAmount())
                        .category(spending.getCategory())
                        .date(spending.getDate())
                        .build())
                .collect(Collectors.toList());
        log.debug("List to return: {}", spendingDTOS);
        return spendingDTOS;
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addUser(@RequestBody UserDTO userDTO) {
        if (userDTO == null) {
            throw new IllegalArgumentException("User didn't provided");
//            return ResponseEntity.badRequest().build();
        }
        //TODO check if user exist?
        User user = User.builder()
                .name(userDTO.getName())
                .build();
        userService.save(user);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
