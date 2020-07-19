package com.project.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

import com.project.minhasfinancas.model.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

}