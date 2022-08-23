package com.bank.BancoDigital.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.SalvarTransferencia;

public interface SalvarTransferenciaDao extends CrudRepository<SalvarTransferencia, Long> {

    List<SalvarTransferencia> findAll();

}
