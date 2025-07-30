package com.br.biblioteca_java.dto;

import com.br.biblioteca_java.model.Livro;

import java.time.LocalDate;

public record LivroSemEmprestimoResponse(
        Long id,
        String titulo,
        String autor,
        boolean disponivel
) {
    public static LivroSemEmprestimoResponse valueOf(Livro livro) {
        return new LivroSemEmprestimoResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.isDisponivel()
        );
    }
}
