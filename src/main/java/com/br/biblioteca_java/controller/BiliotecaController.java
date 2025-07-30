package com.br.biblioteca_java.controller;

import com.br.biblioteca_java.dto.EmprestimoDTOResponse;
import com.br.biblioteca_java.dto.LivroDTOResponse;
import com.br.biblioteca_java.dto.LivroSemEmprestimoResponse;
import com.br.biblioteca_java.dto.UserDTORequest;
import com.br.biblioteca_java.model.Livro;
import com.br.biblioteca_java.model.Usuario;
import com.br.biblioteca_java.repository.LivroRepository;
import com.br.biblioteca_java.repository.UsuarioRepository;
import com.br.biblioteca_java.service.BibliotecaService;
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
@RequestMapping("/biblioteca")
@CrossOrigin("*")
public class BiliotecaController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private LivroService livroService;

    @Autowired
    private BibliotecaService bibliotecaService;


    @GetMapping("/listarLivrosDisponiveis")
    public ResponseEntity<List<LivroSemEmprestimoResponse>> getAllAvaliableLivros() {
        return ResponseEntity.ok(livroRepository.findByDisponivel());
    }

    @PatchMapping("/emprestar/{idUser}/{idLivro}")
    public ResponseEntity<EmprestimoDTOResponse> emprestarLivro(@PathVariable Long idUser, @PathVariable Long idLivro) {
        EmprestimoDTOResponse livroEmprestrado = bibliotecaService.emprestarLivro(idUser, idLivro);
        return ResponseEntity.status(HttpStatus.OK).body(livroEmprestrado);
    }

    @PatchMapping("/devolver/{idUser}/{idLivro}")
    public ResponseEntity<LivroSemEmprestimoResponse> devolverLivro(@PathVariable Long idUser, @PathVariable Long idLivro){
        LivroSemEmprestimoResponse livroDevolvido = bibliotecaService.devolverLivro(idUser, idLivro);

        return ResponseEntity.status(HttpStatus.OK).body(livroDevolvido);
    }


}
