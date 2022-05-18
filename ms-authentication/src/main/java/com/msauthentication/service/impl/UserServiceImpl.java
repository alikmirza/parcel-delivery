package com.msauthentication.service.impl;


import com.msauthentication.entity.User;
import com.msauthentication.repository.UserRepository;
import com.msauthentication.service.UserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getByUsername(String userName) {
        return userRepository.getByUsername(userName);
    }

    @Override
    public boolean validateUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
