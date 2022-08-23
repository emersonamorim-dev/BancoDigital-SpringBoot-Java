package com.bank.BancoDigital.dao;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.security.Funcao;

public interface FuncaoDao extends CrudRepository<Funcao, Integer> {
    Funcao findByNome(String nome);

}
