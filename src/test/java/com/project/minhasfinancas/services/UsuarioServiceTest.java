package com.project.minhasfinancas.services;

import com.project.minhasfinancas.model.entity.Usuario;
import com.project.minhasfinancas.model.repository.UsuarioRepository;
import com.project.minhasfinancas.service.UsuarioService;
import com.project.minhasfinancas.service.impl.UsuarioServiceImpl;
import com.project.minhasfinancas.exception.RegraNegocioException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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

    @Before
    public void setUp() {
        repository = Mockito.mock(UsuarioRepository.class);
        service = new UsuarioServiceImpl(repository);

    }

    @Test(expected = Test.None.class)
    public void deveValidarEmail() {
        // cenario
        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(false);

        // açao
        service.validarEmail("mail@mail.com");
    }

    @Test(expected = RegraNegocioException.class)
    public void deveLancarErroAoValidarEmailQuandoExistirEmailCadastrado() {
        // cenario

        Mockito.when(repository.existsByEmail(Mockito.anyString())).thenReturn(true);

        // acao
        service.validarEmail("mail@mail.com");
    }
}