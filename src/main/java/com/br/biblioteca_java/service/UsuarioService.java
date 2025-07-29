package com.br.biblioteca_java.service;

import com.br.biblioteca_java.dto.LivroDTORequest;
import com.br.biblioteca_java.dto.UserDTORequest;
import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.model.Usuario;
import com.br.biblioteca_java.repository.LivroRepository;
import com.br.biblioteca_java.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    @Transactional
    public Usuario post(UserDTORequest dto) {
        Usuario usuario = new Usuario();
        usuario.setNome(dto.nome());
        usuario.setEmail(dto.email());
        usuario.setMatricula(dto.matricula());
        usuario.setDataNascimento(dto.dataNascimento());
        repository.save(usuario);

        return usuario;
    }

    @Transactional
    public Optional<Usuario> put(Long id, Usuario u){
        return repository.findById(id).map( d -> {
            d.setNome(u.getNome());
            d.setEmail(u.getEmail());
            d.setMatricula(u.getMatricula());
            d.setDataNascimento(u.getDataNascimento());
            return repository.save(d);
        });
    }

}
