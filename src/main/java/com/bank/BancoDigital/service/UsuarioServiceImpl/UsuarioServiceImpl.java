package com.bank.BancoDigital.service.UsuarioServiceImpl;

import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bank.BancoDigital.dao.FuncaoDao;
import com.bank.BancoDigital.dao.UsuarioDao;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.domain.security.FuncaoUsuario;
import com.bank.BancoDigital.service.ContaService;
import com.bank.BancoDigital.service.UsuarioService;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioService.class);

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private FuncaoDao funcaoDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private ContaService contaService;

    public void save(Usuario usuario) {
        usuarioDao.save(usuario);
    }

    @Override
    public Usuario findByUsuario(String usuario) {
        return usuarioDao.findByUsuario(usuario);
    }

    @Override
    public Usuario findByEmail(String email) {
        return usuarioDao.findByEmail(email);
    }

    @Override
    public Usuario createUsuario(Usuario usuario, Set<FuncaoUsuario> funcaoUsuarios) {
        Usuario localUsuario = usuarioDao.findByUsuario(usuario.getUsername());

        if (localUsuario != null) {
            LOG.info("O usuário com nome de usuário {} já existe. Nada será feito. ", usuario.getUsername());
        } else {
            String encryptedPassword = passwordEncoder.encode(usuario.getPassword());
            usuario.setPassword(encryptedPassword);

            for (FuncaoUsuario ur : funcaoUsuarios) {
                funcaoDao.save(ur.getFuncao());
            }

            usuario.getFuncaoUsuarios().addAll(funcaoUsuarios);

            usuario.setContaCorrente(contaService.createContaCorrente());
            usuario.setContaPoupanca(contaService.createContaPoupanca());

            localUsuario = usuarioDao.save(usuario);
        }

        return localUsuario;
    }

    @Override
    public boolean checkUsuarioExists(String usuario, String email) {
        if (checkUsuarioExists(usuario) || checkEmailExists(usuario)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkUsuarioExists(String usuario) {
        if (null != findByUsuario(usuario)) {
            return true;
        }

        return false;
    }

    public boolean checkEmailExists(String email) {
        if (null != findByEmail(email)) {
            return true;
        }

        return false;
    }

    public Usuario saveUsuario(Usuario usuario) {
        return usuarioDao.save(usuario);
    }

    @Override
    public List<Usuario> findUsuarioList() {
        return usuarioDao.findAll();
    }

    public void enableUsuario(String usuario) {
        Usuario user = findByUsuario(usuario);
        user.setEnabled(true);
        usuarioDao.save(user);
    }

    public void disableUsuario(String usuario) {
        Usuario user = findByUsuario(usuario);
        user.setEnabled(false);
        System.out.println(user.isEnabled());
        usuarioDao.save(user);
        System.out.println(usuario + " is disabled.");
    }

}
