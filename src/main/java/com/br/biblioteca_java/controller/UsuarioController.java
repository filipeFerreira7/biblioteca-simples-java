package com.br.biblioteca_java.controller;

import com.br.biblioteca_java.dto.LivroDTORequest;
import com.br.biblioteca_java.dto.UserDTORequest;
import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.model.Usuario;
import com.br.biblioteca_java.repository.LivroRepository;
import com.br.biblioteca_java.repository.UsuarioRepository;
import com.br.biblioteca_java.service.LivroService;
import com.br.biblioteca_java.service.UsuarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Usuario> post(@Valid @RequestBody UserDTORequest dto) {
        var post = usuarioService.post(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> put(@PathVariable Long id,@Valid @RequestBody UserDTORequest dto) {
    Usuario user = new Usuario();
    user.setNome(dto.nome());
    user.setEmail(dto.email());
    user.setMatricula(dto.matricula());
    user.setDataNascimento(dto.dataNascimento());
    return usuarioService.put(id, user).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Usuario> delete(@PathVariable Long id) {
        return usuarioRepository.findById(id)
                .map(u -> {
                    usuarioRepository.delete(u);
                    return ResponseEntity.ok(u);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
