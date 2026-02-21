package com.example.biblioteca.dto;

import java.time.LocalDate;

public record EmprestimoRequisicaoDTO(
        Long livro_id,
        Long usuario_id,
        LocalDate data_emprestimo,
        LocalDate data_devolucao
) {
}
