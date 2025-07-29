package com.br.biblioteca_java.service;

import com.br.biblioteca_java.dto.LivroDTORequest;
import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.repository.LivroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;



    @Transactional
    public Livro post(LivroDTORequest dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setDataEmprestimo(dto.dataEmprestimo());
        livro.setDataDevolucao(calculaDataDevolucao(dto.dataEmprestimo()));

        livroRepository.save(livro);
        return livro;
    }

    @Transactional
    public Optional<Livro> put(Long id, Livro livro){
        return livroRepository.findById(id).map( d -> {
            d.setTitulo(livro.getTitulo());
            d.setAutor(livro.getAutor());
            d.setDataEmprestimo(livro.getDataEmprestimo());

            return livroRepository.save(d);
        });
    }

    public LocalDate calculaDataDevolucao(LocalDate data) {
        return data.plusDays(7);
    }
}
