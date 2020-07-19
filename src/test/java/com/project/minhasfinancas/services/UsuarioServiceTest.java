package com.project.minhasfinancas.services;

import com.project.minhasfinancas.model.entity.Usuario;
import com.project.minhasfinancas.model.repository.UsuarioRepository;
import com.project.minhasfinancas.service.UsuarioService;
import com.project.minhasfinancas.exception.RegraNegocioException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @Autowired
    UsuarioService service;

    @Autowired
    UsuarioRepository repository;

    @Test(expected = Test.None.class)
    public void deveValidarEmail() {
        // cenario
        repository.deleteAll();

        // açao
        service.validarEmail("mail@mail.com");
    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        // cenario
        Usuario usuario = Usuario.builder().nome("Usuario").email("mail@mail.com").build();
        repository.save(usuario);

        // acao
        service.validarEmail(usuario.getEmail());
    }
}