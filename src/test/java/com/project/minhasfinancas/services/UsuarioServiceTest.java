package com.project.minhasfinancas.services;

import com.project.minhasfinancas.model.entity.Usuario;
import com.project.minhasfinancas.model.repository.UsuarioRepository;

import com.project.minhasfinancas.service.impl.UsuarioServiceImpl;

import java.util.Optional;

import com.project.minhasfinancas.exception.ErroAutenticacao;
import com.project.minhasfinancas.exception.RegraNegocioException;

import org.assertj.core.api.Assertions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

    @SpyBean
    UsuarioServiceImpl service;

    @MockBean
    UsuarioRepository repository;

    @Test(expected = Test.None.class)
    public void deveSalverUmUsuario() {
        // Cenário
        Mockito.doNothing().when(service).validarEmail(Mockito.anyString());
        Usuario usuario = Usuario.builder().id(1l).nome("nome").email("mail@mail.com").senha("senha").build();
        Mockito.when(repository.save(Mockito.any(Usuario.class))).thenReturn(usuario);
        // acao
        Usuario usuarioSalvo = service.salvarUsuario(new Usuario());
        // verificacao
        Assertions.assertThat(usuarioSalvo).isNotNull();
        Assertions.assertThat(usuarioSalvo.getId()).isEqualTo(usuario.getId());
        Assertions.assertThat(usuarioSalvo.getNome()).isEqualTo(usuario.getNome());
        Assertions.assertThat(usuarioSalvo.getEmail()).isEqualTo(usuario.getEmail());
        Assertions.assertThat(usuarioSalvo.getSenha()).isEqualTo(usuario.getSenha());
    }

    @Test(expected = RegraNegocioException.class)
    public void naoDeveSalvarUmUsuarioComEmailCadastrado() {
        // Cenario
        Usuario usuario = Usuario.builder().email("mail@mail.com").build();
        Mockito.doThrow(RegraNegocioException.class).when(service).validarEmail(usuario.getEmail());

        // Acao
        service.salvarUsuario(usuario);
        // Verificacao
        Mockito.verify(repository, Mockito.never()).save(usuario);

    }

    @Test(expected = Test.None.class)
    public void deveAutenticarUmUsuarioComSucesso() {
        // cenario
        final String email = "mail@mail.com";
        final String senha = "senha";

        final Usuario usuario = Usuario.builder().email(email).senha(senha).id(1l).build();
        Mockito.when(repository.findByEmail(email)).thenReturn(Optional.of(usuario));
        // acao
        final Usuario result = service.autenticar(email, senha);

        // verificacao
        Assertions.assertThat(result).isNotNull();
    }

    @Test
    public void deveErrorQuandoNaoEncontrarUsuarioCadastradoComOEmailInformado() {
        // cenario
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());

        // acao
        final Throwable exception = Assertions.catchThrowable(() -> service.autenticar("mail@mail.com", "senha"));
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class)
                .hasMessage("Usuário não encontrado para o email encontrado");

    }

    @Test
    public void deveLancarErroQuandoSenhaNaoBater() {
        // cenario
        final String senha = "senha";
        final Usuario usuario = Usuario.builder().email("mail@mail.com").senha(senha).build();
        Mockito.when(repository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(usuario));

        // acao
        final Throwable exception = Assertions.catchThrowable(() -> service.autenticar("email@email", "123"));
        Assertions.assertThat(exception).isInstanceOf(ErroAutenticacao.class).hasMessage("Senha invalida");

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