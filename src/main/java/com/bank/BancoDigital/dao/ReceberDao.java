package com.bank.BancoDigital.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.Receber;

public interface ReceberDao extends CrudRepository<Receber, Long> {
    List<Receber> findAll();

    Receber findByNome(String receberNome);

    void deleteByNome(String receberNome);

}
