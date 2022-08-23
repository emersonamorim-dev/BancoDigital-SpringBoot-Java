package com.bank.BancoDigital.service;

import java.util.List;

import com.bank.BancoDigital.domain.Agendar;

public interface AgendarService {
    Agendar createAgendar(Agendar agendar);

    List<Agendar> findAll();

    Agendar findAgendar(Long id);

    void confirmarAgendar(Long id);

}
