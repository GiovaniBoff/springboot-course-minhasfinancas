package com.project.minhasfinancas.service.impl;

import com.project.minhasfinancas.exception.RegraNegocioException;
import com.project.minhasfinancas.model.entity.Usuario;
import com.project.minhasfinancas.model.repository.UsuarioRepository;
import com.project.minhasfinancas.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private UsuarioRepository repository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public Usuario autenticar(String email, String senha) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Usuario salvarUsuario(Usuario usuario) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void validarEmail(String email) {
        boolean existe = repository.existsByEmail(email);

        if (existe) {
            throw new RegraNegocioException("Já existe um usuário cadastrado com este email");
        }
    }

}