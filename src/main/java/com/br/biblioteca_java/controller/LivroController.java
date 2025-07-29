package com.br.biblioteca_java.controller;

import com.br.biblioteca_java.dto.LivroDTORequest;
import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.repository.LivroRepository;
import com.br.biblioteca_java.service.LivroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
@CrossOrigin("*")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @Autowired
    private LivroRepository livroRepository;


    @GetMapping
    public ResponseEntity<List<Livro>> getAll() {
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Livro> post(@Valid @RequestBody LivroDTORequest dto) {
        var post = livroService.post(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> put(@PathVariable Long id,@Valid @RequestBody LivroDTORequest dto) {
    Livro livro = new Livro();
    livro.setTitulo(dto.titulo());
    livro.setAutor(dto.autor());
    livro.setDataEmprestimo(dto.dataEmprestimo());
    return livroService.put(id, livro).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Livro> delete(@PathVariable Long id) {
        return livroRepository.findById(id)
                .map(livro -> {
                    livroRepository.delete(livro);
                    return ResponseEntity.ok(livro);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
