package com.example.apiTeste.controllers;

import com.example.apiTeste.models.UserModel;
import com.example.apiTeste.services.UserService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> getUser() {
        return ResponseEntity.ok("Sucesso !!!");
    }
}


