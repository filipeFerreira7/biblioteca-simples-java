package com.br.biblioteca_java.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "livros")
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String titulo;

    @Column
    private String autor;

    @Column
    private LocalDate dataEmprestimo;

    @Column
    private LocalDate dataDevolucao;

    @Column(nullable = false)
    private boolean disponivel;

    @ManyToOne
    private Usuario usuario;
}
