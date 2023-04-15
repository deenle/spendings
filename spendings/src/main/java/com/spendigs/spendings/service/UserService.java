package com.spendigs.spendings.service;

import com.spendigs.spendings.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final List<User> users = new ArrayList<>();
    {
        users.add(new User( "Jack"));
        System.out.println("Users: " + users);
    }

    public User findUser(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst().orElse(null);
    }
}
