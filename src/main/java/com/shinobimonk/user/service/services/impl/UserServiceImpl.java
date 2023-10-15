package com.shinobimonk.user.service.services.impl;

import com.shinobimonk.user.service.entities.Rating;
import com.shinobimonk.user.service.entities.User;
import com.shinobimonk.user.service.exception.ResourceNotFoundException;
import com.shinobimonk.user.service.repository.UserRepository;
import com.shinobimonk.user.service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RestTemplate restTemplate;
    @Override
    public User saveUser(User user) {

        String id = UUID.randomUUID().toString();
        user.setUserId(id);
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = userRepository.findAll();
        List<User> updatedUserList = userList.stream().map(user -> {
            ResponseEntity<ArrayList> ratingEntity = restTemplate.getForEntity("http://localhost:8083/rating/user/" + user.getUserId(), ArrayList.class);
            user.setRating(ratingEntity.getBody());
            return user;
        }).collect(Collectors.toList());

        return updatedUserList;
    }

    @Override
    public User getUserById(String userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User with given ID is not found on Server !! Id : " + userId));

//        http://localhost:8083/rating/user/c4460112-88c0-456c-8cfb-908910a95a8b
        //TODO implemetation pending
        ResponseEntity<List<Rating>> ratingEntity = restTemplate.getForEntity("http://localhost:8083/rating/user/" + user.getUserId(), ArrayList.class);


        user.setRating(ratingEntity.getBody());
        return user;
    }

    @Override
    public void deleteUser(String userId) {

    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
