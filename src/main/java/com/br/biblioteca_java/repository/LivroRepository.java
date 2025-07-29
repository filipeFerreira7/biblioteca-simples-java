package com.br.biblioteca_java.repository;

import com.br.biblioteca_java.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}
