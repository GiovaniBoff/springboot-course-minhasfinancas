package com.project.minhasfinancas.model.repository;

import com.project.minhasfinancas.model.entity.Lancamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}