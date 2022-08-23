package com.bank.BancoDigital.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bank.BancoDigital.domain.Usuario;

public interface UsuarioDao extends CrudRepository<Usuario, Long> {
    Usuario findByUsuario(Usuario usuario);

    Usuario findByEmail(String email);

    List<Usuario> findAll();

    Usuario findByUsuario(String usuario);

}
