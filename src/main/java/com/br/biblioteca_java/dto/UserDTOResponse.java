package com.br.biblioteca_java.dto;

import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.model.Usuario;

import java.time.LocalDate;

public record UserDTOResponse(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String email,
        String matricula
) {
    public static UserDTOResponse valueOf(Usuario usuario) {
        return new UserDTOResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                usuario.getMatricula()
        );
    }
}
