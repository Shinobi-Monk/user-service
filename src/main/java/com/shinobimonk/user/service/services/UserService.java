package com.shinobimonk.user.service.services;

import com.shinobimonk.user.service.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers();
    User getUserById(String userId);

    void deleteUser(String userId);

    User updateUser(User user);
}
