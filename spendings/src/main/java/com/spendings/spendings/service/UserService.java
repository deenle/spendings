package com.spendings.spendings.service;

import com.spendings.spendings.model.User;
import com.spendings.spendings.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    final Logger logger = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public User findOne(int userId) {
        logger.debug("findOne for id: {} from {} class working", userId, UserService.class.getSimpleName());
        return userRepository.findById(userId).orElse(null);
    }

    public List<User> findAll() {
        logger.debug("findAll from {} class working", UserService.class.getSimpleName());
        return userRepository.findAll();
    }

}
