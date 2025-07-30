package com.br.biblioteca_java.dto;

import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.model.Usuario;

public record EmprestimoDTOResponse(
       LivroDTOResponse livro,
       String matriculaUser
) {
    public static EmprestimoDTOResponse valueOf(Livro livro, String matriculaUser) {
        return new EmprestimoDTOResponse(
               new LivroDTOResponse(
                       livro.getId(),
                       livro.getTitulo(),
                       livro.getAutor(),
                       livro.getDataEmprestimo(),
                       livro.getDataDevolucao(),
                       livro.isDisponivel()
               ),
                matriculaUser
        );

    }
}
