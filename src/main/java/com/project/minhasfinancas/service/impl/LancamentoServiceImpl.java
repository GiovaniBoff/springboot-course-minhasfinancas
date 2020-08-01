package com.project.minhasfinancas.service.impl;

import java.util.List;

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
    public Lancamento atualizar(Lancamento lancamento) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deletar(Lancamento lancamento) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<Lancamento> buscar(Lancamento lancamentoFiltro) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void atualizarStatus(Lancamento lancamento, StatusLancamento status) {
        // TODO Auto-generated method stub

    }

}
