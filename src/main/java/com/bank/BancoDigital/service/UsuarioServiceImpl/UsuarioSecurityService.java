package com.bank.BancoDigital.service.UsuarioServiceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bank.BancoDigital.dao.UsuarioDao;
import com.bank.BancoDigital.domain.Usuario;

@Service
public class UsuarioSecurityService implements UserDetailsService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioSecurityService.class);

    @Autowired
    private UsuarioDao usuarioDao;

    @Override
    public UserDetails loadUserByUsername(String usuario) throws UsernameNotFoundException {
        Usuario user = usuarioDao.findByUsuario(usuario);
        if (null == usuario) {
            LOG.warn("Usuario {} não encontrado", usuario);
            throw new UsernameNotFoundException("Usuario " + usuario + " not found");
        }
        return user;
    }
}
