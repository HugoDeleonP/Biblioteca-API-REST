package com.example.biblioteca.dto;

public record LivroRespostaDTO(
        Long id,
        String titulo,
        String autor,
        int ano_publicacao) {
}
