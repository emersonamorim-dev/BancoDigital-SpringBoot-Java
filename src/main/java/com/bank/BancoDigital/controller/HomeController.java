package com.bank.BancoDigital.controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.BancoDigital.dao.FuncaoDao;
import com.bank.BancoDigital.domain.ContaCorrente;
import com.bank.BancoDigital.domain.ContaPoupanca;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.domain.security.FuncaoUsuario;
import com.bank.BancoDigital.service.UsuarioService;

@Controller
public class HomeController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private FuncaoDao funcaoDao;

    @RequestMapping("/")
    public String home() {
        return "redirect:/index";

    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.GET)
    public String cadastro(Model model) {
        Usuario usuario = new Usuario();

        model.addAttribute("usuario", usuario);

        return "cadastro";

    }

    @RequestMapping(value = "/cadastro", method = RequestMethod.POST)
    public String cadastroPost(@ModelAttribute("usuario") Usuario usuario, Model model) {

        if (usuarioService.checkUsuarioExists(usuario.getUsername(), usuario.getEmail())) {

            if (usuarioService.checkEmailExists(usuario.getEmail())) {
                model.addAttribute("emailExists", true);
            }

            if (usuarioService.checkUsuarioExists(usuario.getUsername())) {
                model.addAttribute("usuarioExists", true);
            }

            return "cadastro";
        } else {
            Set<FuncaoUsuario> funcaoUsuarios = new HashSet<>();
            funcaoUsuarios.add(new FuncaoUsuario(usuario, funcaoDao.findByNome("FUNCAO_USUARIO")));

            usuarioService.createUsuario(usuario, funcaoUsuarios);

            return "redirect:/";
        }
    }

    @RequestMapping("/bank")
    public String bank(Principal principal, Model model) {
        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        ContaCorrente contaCorrente = usuario.getContaCorrente();
        ContaPoupanca contaPoupanca = usuario.getContaPoupanca();

        model.addAttribute("contaCorrente", contaCorrente);
        model.addAttribute("contaPoupanca", contaPoupanca);

        return "bank";
    }
}
