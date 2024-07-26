package com.example.apiTeste.controllers;

import com.example.apiTeste.models.UserModel;
import com.example.apiTeste.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
        var newUser = userService.createUser(user);
        BCryptPasswordEncoder cript = new BCryptPasswordEncoder();
        var hash = cript.encode(newUser.getPassword());
        newUser.setPassword(hash);
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping("/user")
    public ResponseEntity<Object> listUser() {
        var listUser = userService.listAllUsers();
        return ResponseEntity.ok().body(listUser);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<Object> listUserId(@PathVariable UUID id) {
        var user = userService.listId(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("user not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserModel user, @PathVariable UUID id) {
        var userId = userService.listId(id);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
        userId.setLogin(user.getLogin());
        userId.setPassword(user.getPassword());
        userId.setEmail(user.getEmail());
        var updateUser = userService.createUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(updateUser);

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable UUID id) {
        var userId = userService.listId(id);
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not Found");
        }
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body("User Deleted");
    }

}
