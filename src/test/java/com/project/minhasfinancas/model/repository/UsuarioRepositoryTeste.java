package com.project.minhasfinancas.model.repository;

import com.project.minhasfinancas.model.entity.Usuario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
public class UsuarioRepositoryTeste {

    @Autowired
    UsuarioRepository repository;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        // cenario
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@mail.com").build();
        repository.save(usuario);
        // ação/ execução
        boolean result = repository.existsByEmail(usuario.getEmail());

        // verificação
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
        // cenáro
        repository.deleteAll();

        // ação
        boolean result = repository.existsByEmail("usuario@email.com");

        // verificação
        Assertions.assertThat(result).isFalse();

    }

}