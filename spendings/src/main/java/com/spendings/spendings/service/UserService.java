package com.spendings.spendings.service;

import com.spendings.spendings.model.User;
import com.spendings.spendings.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
//    private final CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
//    {
//        users.add(new User( "Jack"));
//        System.out.println("Users: " + users);
//    }

    private final UserRepository userRepository;

    public User findOne(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
    // Necessity of method?
//    public List<User> findUser(String userName) {
//        return users.stream()
//                .filter(user -> user.getName().equalsIgnoreCase(userName))
//                .collect(Collectors.toList());
//    }
}
