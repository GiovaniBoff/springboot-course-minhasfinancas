package com.project.minhasfinancas.service;

import com.project.minhasfinancas.model.entity.Usuario;
import java.util.List;

public interface UsuarioService {

    Usuario autenticar(String email, String senha);

    Usuario salvarUsuario(Usuario usuario);

    List<Usuario> buscarUsuario();

    void validarEmail(String email);

}