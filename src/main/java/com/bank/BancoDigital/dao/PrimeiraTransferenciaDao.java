package com.bank.BancoDigital.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.PrimeiraTransferencia;

public interface PrimeiraTransferenciaDao extends CrudRepository<PrimeiraTransferencia, Long> {

    List<PrimeiraTransferencia> findAll();

}
