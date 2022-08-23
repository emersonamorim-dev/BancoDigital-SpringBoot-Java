package com.bank.BancoDigital.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class SalvarTransferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date;
    private String descricao;
    private String tipo;
    private String status;
    private double valor;
    private BigDecimal saldoDisponivel;

    @ManyToOne
    @JoinColumn(name = "salvar_conta_id")
    private ContaPoupanca contaPoupanca;

    public SalvarTransferencia() {
    }

    public SalvarTransferencia(Date date, String descricao, String tipo, String status, double valor,
            BigDecimal saldoDisponivel, ContaPoupanca contaPoupanca) {

        this.date = date;
        this.descricao = descricao;
        this.tipo = tipo;
        this.status = status;
        this.valor = valor;
        this.saldoDisponivel = saldoDisponivel;
        this.contaPoupanca = contaPoupanca;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public BigDecimal getSaldoDisponivel() {
        return saldoDisponivel;
    }

    public void setSaldoDisponivel(BigDecimal saldoDisponivel) {
        this.saldoDisponivel = saldoDisponivel;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }
}
