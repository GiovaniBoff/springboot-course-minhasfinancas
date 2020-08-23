package com.project.minhasfinancas.service.impl;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;

import com.project.minhasfinancas.model.entity.Lancamento;
import com.project.minhasfinancas.model.enums.StatusLancamento;
import com.project.minhasfinancas.model.repository.LancamentoRepository;
import com.project.minhasfinancas.service.LancamentoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository repository;

    @Override
    @Transactional
    public Lancamento salvar(Lancamento lancamento) {

        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public Lancamento atualizar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        return repository.save(lancamento);
    }

    @Override
    @Transactional
    public void deletar(Lancamento lancamento) {
        Objects.requireNonNull(lancamento.getId());
        repository.delete(lancamento);
    }

    @Override
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        lancamento.setStatus(status);
        atualizar(lancamento);
    }

}
