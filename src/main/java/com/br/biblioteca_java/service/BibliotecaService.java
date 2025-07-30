package com.br.biblioteca_java.service;

import com.br.biblioteca_java.dto.EmprestimoDTOResponse;
import com.br.biblioteca_java.dto.LivroDTOResponse;
import com.br.biblioteca_java.dto.LivroSemEmprestimoResponse;
import com.br.biblioteca_java.exception.EmprestimoException;
import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.model.Usuario;
import com.br.biblioteca_java.repository.LivroRepository;
import com.br.biblioteca_java.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BibliotecaService {
    @Autowired
    private LivroRepository livroRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    // aqui preciso setar as regras de negocios de ->
    // Emprestar livro, Devolver livro, Listar livros disponíveis

    @Transactional
    public EmprestimoDTOResponse emprestarLivro(Long idUsuario, Long idLivro) {
      Livro livro = livroRepository.findById(idLivro).orElseThrow(() -> new EmprestimoException("erro", "Livro não encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new EmprestimoException("erro","Usuário não encontrado"));

        if (!livro.isDisponivel()) {
            throw new EmprestimoException("erro","Livro já emprestado");
        }
        livro.setDisponivel(false);
        livro.setDataEmprestimo(LocalDate.now());
        livro.setDataDevolucao(calculaDataDevolucao(livro.getDataEmprestimo()));
        livro.setUsuario(usuario);

       return EmprestimoDTOResponse.valueOf(livro,usuario.getMatricula());
    }

    @Transactional
    public LivroSemEmprestimoResponse devolverLivro(Long idUsuario, Long idLivro) {
        Livro livro = livroRepository.findById(idLivro).orElseThrow(() -> new EmprestimoException("erro", "Livro não encontrado"));
        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new EmprestimoException("erro","Usuário não encontrado"));

        if (livro.isDisponivel()) {
            throw new EmprestimoException("erro","Livro já está disponivel");
        }

        if(!livro.getUsuario().getId().equals(usuario.getId())) {
            throw new EmprestimoException("erro", "Este livro não foi emprestado para este usuário");
        }
        livro.setUsuario(null);
        livro.setDisponivel(true);
        livro.setDataEmprestimo(null);
        livro.setDataDevolucao(null);

        return LivroSemEmprestimoResponse.valueOf(livro);
    }

    public LocalDate calculaDataDevolucao(LocalDate data) {
        return data.plusDays(7);
    }
}
