package com.br.biblioteca_java.repository;

import com.br.biblioteca_java.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
