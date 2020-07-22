package com.project.minhasfinancas.model.repository;

import java.util.Optional;

import com.project.minhasfinancas.model.entity.Usuario;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UsuarioRepositoryTeste {

    @Autowired
    UsuarioRepository repository;

    @Autowired
    TestEntityManager entityManager;

    @Test
    public void deveVerificarAExistenciaDeUmEmail() {
        // cenario
        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@mail.com").build();
        entityManager.persist(usuario);
        // ação/ execução
        boolean result = repository.existsByEmail(usuario.getEmail());

        // verificação
        Assertions.assertThat(result).isTrue();
    }

    @Test
    public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
        // cenáro

        // ação
        boolean result = repository.existsByEmail("usuario@email.com");

        // verificação
        Assertions.assertThat(result).isFalse();

    }

    @Test

    public void devePersistirUmUsuarioNaBaseDeDados() {
        // cenário
        Usuario usuario = criarUsuario();

        // acao

        Usuario usuarioSalvo = repository.save(usuario);

        // Verificacao
        Assertions.assertThat(usuarioSalvo.getId()).isNotNull();

    }

    @Test
    public void deveBuscarUmUsuarioPorEmail() {
        // cenario
        Usuario usuario = criarUsuario();
        entityManager.persist(usuario);

        // acao
        Optional<Usuario> result = repository.findByEmail(usuario.getEmail());

        // verificacao
        Assertions.assertThat(result.isPresent()).isTrue();
    }

    @Test
    public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExistirNaBase() {
        // cenario

        // acao
        Optional<Usuario> result = repository.findByEmail("usuario@mail.com");

        // verificacao
        Assertions.assertThat(result.isPresent()).isFalse();
    }

    public static Usuario criarUsuario() {

        Usuario usuario = Usuario.builder().nome("usuario").email("usuario@mail.com").senha("senha").build();

        return usuario;
    }

}