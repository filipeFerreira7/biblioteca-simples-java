package com.br.biblioteca_java.service;

import com.br.biblioteca_java.dto.LivroDTORequest;
import com.br.biblioteca_java.dto.LivroDTOResponse;
import com.br.biblioteca_java.dto.LivroSemEmprestimoResponse;
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
    public LivroSemEmprestimoResponse post(LivroDTORequest dto) {
        Livro livro = new Livro();
        livro.setTitulo(dto.titulo());
        livro.setAutor(dto.autor());
        livro.setDisponivel(true);
        livroRepository.save(livro);
        return LivroSemEmprestimoResponse.valueOf(livro);
    }

    @Transactional
    public Optional<LivroSemEmprestimoResponse> put(Long id, Livro livro){
        return livroRepository.findById(id).map( d -> {
            d.setTitulo(livro.getTitulo());
            d.setAutor(livro.getAutor());
            return LivroSemEmprestimoResponse.valueOf(livroRepository.save(d));
        });
    }


}
