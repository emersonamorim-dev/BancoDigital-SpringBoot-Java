package com.bank.BancoDigital.dao;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.ContaCorrente;

public interface ContaCorrenteDao extends CrudRepository<ContaCorrente, Long> {

    ContaCorrente findByNumeroConta(int numeroConta);

}
