package com.bank.BancoDigital.domain;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bank.BancoDigital.domain.security.Authority;
import com.bank.BancoDigital.domain.security.FuncaoUsuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userId", nullable = false, updatable = false)
    private Long userId;
    private String username;
    private String password;
    private String nome;
    private String sobrenome;

    @Column(name = "email", nullable = false, unique = true)
    private String email;
    private String telefone;

    private boolean enabled = true;

    @OneToOne
    private ContaCorrente contaCorrente;

    @OneToOne
    private ContaPoupanca contaPoupanca;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Agendar> agendaList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Receber> receberList;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<FuncaoUsuario> funcaoUsuarios = new HashSet<>();

    public Set<FuncaoUsuario> getFuncaoUsuarios() {
        return funcaoUsuarios;
    }

    public void setFuncaoUsuarios(Set<FuncaoUsuario> funcaoUsuarios) {
        this.funcaoUsuarios = funcaoUsuarios;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.userId = usuarioId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        this.username = Username;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Agendar> getAgendarList() {
        return agendaList;
    }

    public void setAgendarList(List<Agendar> agendarList) {
        this.agendaList = agendarList;
    }

    public List<Receber> getReceberList() {
        return receberList;
    }

    public void setReceberList(List<Receber> receberList) {
        this.receberList = receberList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ContaCorrente getContaCorrente() {
        return contaCorrente;
    }

    public void setContaCorrente(ContaCorrente contaCorrente) {
        this.contaCorrente = contaCorrente;
    }

    public ContaPoupanca getContaPoupanca() {
        return contaPoupanca;
    }

    public void setContaPoupanca(ContaPoupanca contaPoupanca) {
        this.contaPoupanca = contaPoupanca;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", passowrd='" + password + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", agendaList=" + agendaList +
                ", receberList=" + receberList +
                ", funcaoUsuario=" + funcaoUsuarios +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<>();
        funcaoUsuarios.forEach(ur -> authorities.add(new Authority(ur.getFuncao().getNome())));
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

}