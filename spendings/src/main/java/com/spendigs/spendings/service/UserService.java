package com.spendigs.spendings.service;

import com.spendigs.spendings.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    {
        users.add(new User( "Jack"));
        System.out.println("Users: " + users);
    }

    public User findUser(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst().orElse(null);
    }

    // Necessity of method?
    public List<User> findUser(String userName){
        return  users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }
}
