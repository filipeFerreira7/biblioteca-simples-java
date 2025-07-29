package com.br.biblioteca_java.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record LivroDTORequest(
        @NotBlank(message = "O título não pode estar em branco")
        @NotNull(message = "O campo título é obrigatório")
        String titulo,
        @NotBlank(message = "O campo autor não pode estar em branco")
        @NotNull(message = "O campo autor é obrigatório")
        String autor,
        @NotNull
        @FutureOrPresent(message = "A data de empréstimo não pode ser no passado")
        LocalDate dataEmprestimo
) {
}
