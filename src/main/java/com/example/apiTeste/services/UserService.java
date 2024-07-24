package com.example.apiTeste.services;

import com.example.apiTeste.models.UserModel;
import com.example.apiTeste.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service

public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel createUser(UserModel user) {
        return userRepository.save(user);
    }

    public List<UserModel> listAllUsers() {
        return userRepository.findAll();
    }

    public UserModel listId(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }


}
