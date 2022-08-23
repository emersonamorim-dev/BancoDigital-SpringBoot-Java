package com.bank.BancoDigital.dao;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.ContaPoupanca;

public interface ContaPoupancaDao extends CrudRepository<ContaPoupanca, Long> {

    ContaPoupanca findByNumeroConta(int numeroConta);

}
