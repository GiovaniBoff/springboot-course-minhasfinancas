package com.project.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}