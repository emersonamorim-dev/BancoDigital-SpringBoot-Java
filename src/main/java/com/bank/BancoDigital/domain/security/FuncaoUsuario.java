package com.bank.BancoDigital.domain.security;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.bank.BancoDigital.domain.Usuario;

@Entity
@Table(name = "funcao_usuario")
public class FuncaoUsuario {

    private long funcaoUsuarioId;

    public FuncaoUsuario(Usuario usuario, Funcao funcao) {
        this.usuario = usuario;
        this.funcao = funcao;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Funcao funcao;

    public long getFuncaoUsuarioId() {
        return funcaoUsuarioId;
    }

    public void setFuncaoUsuarioId(long funcaoUsuarioId) {
        this.funcaoUsuarioId = funcaoUsuarioId;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }

}
