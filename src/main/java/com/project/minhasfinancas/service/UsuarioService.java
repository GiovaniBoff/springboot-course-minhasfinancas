package com.project.minhasfinancas.service;

import com.project.minhasfinancas.model.entity.Usuario;
import java.util.List;
import java.util.Optional;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    List<Usuario> buscarUsuario();

    void validarEmail(String email);

    Optional<Usuario> obterPorId(Long id);

}