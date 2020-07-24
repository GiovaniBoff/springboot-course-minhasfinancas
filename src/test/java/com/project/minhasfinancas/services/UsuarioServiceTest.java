package com.project.minhasfinancas.services;

import com.project.minhasfinancas.model.entity.Usuario;
import com.project.minhasfinancas.model.repository.UsuarioRepository;
import com.project.minhasfinancas.service.UsuarioService;
import com.project.minhasfinancas.service.impl.UsuarioServiceImpl;

import java.util.Optional;

import com.project.minhasfinancas.exception.ErroAutenticacao;
import com.project.minhasfinancas.exception.RegraNegocioException;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    UsuarioService service;

    @MockBean
    UsuarioRepository repository;

    @Before
    public void setUp() {
        service = new UsuarioServiceImpl(repository);
    }

    @Test(expected = Test.None.class)
    public void deveAutenticarUmUsuarioComSucesso() {
        // cenario
        String email = "mail@mail.com";
        String senha = "senha";

        Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
        // acao
        Usuario result = service.autenticar(email, senha);

        // verificacao
        Assertions.assertThat(result).isNotNull();
    }

    @Test(expected = ErroAutenticacao.class)
    public void deveErrorQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
        // cenario
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        // acao
        service.autenticar("mail@mail.com", "senha");

    }

    @Test(expected = ErroAutenticacao.class)
    public void deveLancarErroQuandoSenhaNaoBater() {
        // cenario
        String senha = "senha";
        Usuario usuario = Usuario.builder().email("mail@mail.com").senha(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        // acao
        service.autenticar("email@email", "123");

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