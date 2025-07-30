package com.br.biblioteca_java.repository;

import com.br.biblioteca_java.dto.LivroSemEmprestimoResponse;
import com.br.biblioteca_java.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT l FROM Livro l WHERE l.disponivel = true")
    List<LivroSemEmprestimoResponse> findByDisponivel();

}
