package com.bank.BancoDigital.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BancoDigital.domain.PrimeiraTransferencia;
import com.bank.BancoDigital.domain.SalvarTransferencia;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.service.TransferenciaService;
import com.bank.BancoDigital.service.UsuarioService;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasFuncao('ADMIN'")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TransferenciaService transferenciaService;

    @RequestMapping(value = "/usuario/all", method = RequestMethod.GET)
    public List<Usuario> usuariorList() {
        return usuarioService.findUsuarioList();
    }

    @RequestMapping(value = "/usuario/primeira/transferencia", method = RequestMethod.GET)
    public List<PrimeiraTransferencia> getPrimeiraTransferenciaList(@RequestParam("usuario") String usuario) {
        return transferenciaService.findPrimeiraTransferenciaList(usuario);
    }

    @RequestMapping(value = "/usuario/salvar/transferencia", method = RequestMethod.GET)
    public List<SalvarTransferencia> getSalvarTransferenciaList(@RequestParam("usuario") String usuario) {
        return transferenciaService.findSalvarTransferenciaList(usuario);
    }

    @RequestMapping("/usuario/{usuario}/enable")
    public void enableUsuario(@PathVariable("usuario") String usuario) {
        usuarioService.enableUsuario(usuario);
    }

    @RequestMapping("/usuario/{usuario}/disable")
    public void diableUsuario(@PathVariable("usuario") String usuario) {
        usuarioService.disableUsuario(usuario);
    }
}
