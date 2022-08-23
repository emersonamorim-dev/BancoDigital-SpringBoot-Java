package com.bank.BancoDigital.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.Agendar;

public interface AgendarDao extends CrudRepository<Agendar, Long> {

    List<Agendar> findAll();

    Agendar findOne(Long id);

}
