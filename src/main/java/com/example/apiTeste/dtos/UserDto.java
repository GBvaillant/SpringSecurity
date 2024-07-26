package com.example.apiTeste.dtos;

import jakarta.validation.constraints.NotBlank;

public record UserDto(@NotBlank String login, @NotBlank String email, @NotBlank String password) {
}
