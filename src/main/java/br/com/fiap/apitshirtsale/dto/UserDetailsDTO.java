package br.com.fiap.apitshirtsale.dto;

public record UserDetailsDTO(
        Long id,
        String name,
        String username,
        String password
) {}
