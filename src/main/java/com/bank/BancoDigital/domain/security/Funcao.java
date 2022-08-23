package com.bank.BancoDigital.domain.security;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Funcao {

    @Id
    private int funcaoId;

    private String nome;

    @OneToOne(mappedBy = "funcao", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FuncaoUsuario> funcaoUsuarios = new HashSet<>();

    public Funcao() {

    }

    public int getFuncaoId() {
        return funcaoId;
    }

    public void setFuncaoId(int funcaoId) {
        this.funcaoId = funcaoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<FuncaoUsuario> getFuncaoUsuarios() {
        return funcaoUsuarios;
    }

    public void setFuncaoUsuarios(Set<FuncaoUsuario> funcaoUsuarios) {
        this.funcaoUsuarios = funcaoUsuarios;
    }

}
