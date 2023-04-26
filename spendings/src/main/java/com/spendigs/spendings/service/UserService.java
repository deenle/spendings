package com.spendigs.spendings.service;

import com.spendigs.spendings.model.User;
import com.spendigs.spendings.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
//    private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
//
//    {
//        users.add(new User( "Jack"));
//        System.out.println("Users: " + users);
//    }

    private final UserRepository userRepository;

    public User findOne(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    // Necessity of method?
    public List<User> findUser(String userName) {
        return users.stream()
                .filter(user -> user.getName().equalsIgnoreCase(userName))
                .collect(Collectors.toList());
    }
}
