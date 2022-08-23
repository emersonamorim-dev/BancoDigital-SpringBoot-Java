package com.bank.BancoDigital.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ContaCorrente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int numeroConta;
    private BigDecimal saldoConta;

    @OneToMany(mappedBy = "contaCorrente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<PrimeiraTransferencia> primeiraTransferenciaList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public BigDecimal getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(BigDecimal saldoConta) {
        this.saldoConta = saldoConta;
    }

    public List<PrimeiraTransferencia> getPrimeiraTransferenciaList() {
        return primeiraTransferenciaList;
    }

    public void setPrimeiraTransferenciaList(List<PrimeiraTransferencia> primeiraTransferenciaList) {
        this.primeiraTransferenciaList = primeiraTransferenciaList;
    }

}