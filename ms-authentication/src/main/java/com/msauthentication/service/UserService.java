package com.msauthentication.service;

import com.msauthentication.entity.User;

import javax.swing.text.html.Option;
import java.util.Optional;


public interface UserService {

    User create(User user);
    Optional<User> getByUsername(String userName);
    boolean validateUsername(String username);
}
