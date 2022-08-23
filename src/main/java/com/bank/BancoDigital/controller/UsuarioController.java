package com.bank.BancoDigital.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.service.UsuarioService;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public String perfil(Principal principal, Model model) {
        Usuario usuario = usuarioService.findByUsuario(principal.getName());

        model.addAttribute("usuario", usuario);

        return "perfil";

    }

    public String perfilPost(@ModelAttribute("usuario") Usuario newUsuario, Model model) {
        Usuario usuario = usuarioService.findByUsuario(newUsuario.getUsername());
        usuario.setUsername(newUsuario.getUsername());
        usuario.setNome(newUsuario.getNome());
        usuario.setSobrenome(newUsuario.getSobrenome());
        usuario.setEmail(newUsuario.getEmail());
        usuario.setTelefone(newUsuario.getTelefone());

        model.addAttribute("usuario", usuario);

        usuarioService.saveUsuario(usuario);

        return "perfil";

    }

}
