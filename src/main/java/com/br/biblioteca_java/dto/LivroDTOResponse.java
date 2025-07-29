package com.br.biblioteca_java.dto;

import com.br.biblioteca_java.model.Livro;

import java.time.LocalDate;

public record LivroDTOResponse(
        Long id,
        String titulo,
        String autor,
        LocalDate dataEmprestimo,
        LocalDate dataDevolucao
) {
    public static LivroDTOResponse valueOf(Livro livro) {
        return new LivroDTOResponse(
                livro.getId(),
                livro.getTitulo(),
                livro.getAutor(),
                livro.getDataEmprestimo(),
                livro.getDataDevolucao()
        );
    }
}
