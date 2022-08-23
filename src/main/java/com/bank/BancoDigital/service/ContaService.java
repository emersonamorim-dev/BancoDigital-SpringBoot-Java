package com.bank.BancoDigital.service;

import java.security.Principal;

import com.bank.BancoDigital.domain.ContaCorrente;
import com.bank.BancoDigital.domain.PrimeiraTransferencia;
import com.bank.BancoDigital.domain.ContaPoupanca;
import com.bank.BancoDigital.domain.SalvarTransferencia;

public interface ContaService {
    public ContaCorrente createContaCorrente();

    public ContaPoupanca createContaPoupanca();

    public void deposito(String tipoConta, double valor, Principal principal);

    public void saque(String tipoConta, double valor, Principal principal);

}
