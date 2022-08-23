package com.bank.BancoDigital.service;

import java.security.Principal;
import java.util.List;

import com.bank.BancoDigital.domain.ContaCorrente;
import com.bank.BancoDigital.domain.PrimeiraTransferencia;
import com.bank.BancoDigital.domain.Receber;
import com.bank.BancoDigital.domain.ContaPoupanca;
import com.bank.BancoDigital.domain.SalvarTransferencia;

public interface TransferenciaService {
        List<PrimeiraTransferencia> findPrimeiraTransferenciaList(String usuario);

        List<SalvarTransferencia> findSalvarTransferenciaList(String usuario);

        void savePrimeiroDepositoTransferido(PrimeiraTransferencia primeiraTransferencia);

        void saveSalvarDepositoTransferido(SalvarTransferencia salvarTransferencia);

        void savePrimeiroSaqueTransferido(PrimeiraTransferencia primeiraTransferencia);

        void saveSalvarSaqueTransferido(SalvarTransferencia salvarTransferencia);

        void betweenContaTransferencia(String TransferidoDe, String transferidoPara, String valor,
                        ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) throws Exception;

        List<Receber> findReceberList(Principal principal);

        Receber saveReceber(Receber receber);

        Receber findReceberByNome(String receberNome);

        void deleteReceberByNome(String receberNome);

        void toSomeoneElseTransferir(Receber receber, String tipoConta, String valor, ContaCorrente contaCorrente,
                        ContaPoupanca contaPoupanca);

}
