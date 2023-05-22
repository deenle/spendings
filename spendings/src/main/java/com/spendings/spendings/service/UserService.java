package com.spendings.spendings.service;

import com.spendings.spendings.model.User;
import com.spendings.spendings.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public User findOne(int userId) {
        log.debug("findOne for id: {} from {} class working", userId, UserService.class.getSimpleName());

        return userRepository.getReferenceById(userId);
    }

    public List<User> findAll() {
        log.debug("findAll from {} class working", UserService.class.getSimpleName());
        return userRepository.findAll();
    }

    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }
}
