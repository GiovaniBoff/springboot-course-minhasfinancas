package com.project.minhasfinancas.model.repository;

import com.project.minhasfinancas.model.entity.Usuario;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
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

}