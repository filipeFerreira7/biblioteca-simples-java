package com.br.biblioteca_java.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDate;

public record UserDTORequest(
        @NotNull(message = "O campo nome não pode ser nulo")
        @NotBlank(message = "O campo nome não pode ser branco")
        String nome,

        @NotNull(message = "O campo nome não pode ser nulo")
        @PastOrPresent(message = "A data não pode estar no futuro")
        LocalDate dataNascimento,

        @NotNull(message = "O campo email não pode ser nulo")
        @NotBlank(message = "O campo email não pode ser branco")
        String email,

        @NotNull(message = "O campo matricula não pode ser nulo")
        @NotBlank(message = "O campo matricula não pode ser branco")
        String matricula
) {
}
