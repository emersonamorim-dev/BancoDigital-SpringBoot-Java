package com.bank.BancoDigital.service;

import java.util.List;
import java.util.Set;

import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.domain.security.FuncaoUsuario;

public interface UsuarioService {
    Usuario findByUsuario(String usuario);

    Usuario findByEmail(String email);

    boolean checkUsuarioExists(String usuario, String email);

    boolean checkUsuarioExists(String usuario);

    boolean checkEmailExists(String email);

    void save(Usuario usuario);

    Usuario createUsuario(Usuario usuario, Set<FuncaoUsuario> funcaoUsuarios);

    Usuario saveUsuario(Usuario usuario);

    List<Usuario> findUsuarioList();

    void enableUsuario(String usuario);

    void disableUsuario(String usuario);

}
