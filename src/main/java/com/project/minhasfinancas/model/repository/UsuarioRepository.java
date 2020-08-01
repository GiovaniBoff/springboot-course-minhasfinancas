package com.project.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

import com.project.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    Optional<Usuario> findByEmail(String email);

}