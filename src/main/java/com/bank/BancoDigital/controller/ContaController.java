package com.bank.BancoDigital.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.BancoDigital.domain.ContaCorrente;
import com.bank.BancoDigital.domain.PrimeiraTransferencia;
import com.bank.BancoDigital.domain.ContaPoupanca;
import com.bank.BancoDigital.domain.SalvarTransferencia;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.service.ContaService;
import com.bank.BancoDigital.service.TransferenciaService;
import com.bank.BancoDigital.service.UsuarioService;

@Controller
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ContaService contaService;

    @Autowired
    private TransferenciaService transferenciaService;

    @RequestMapping("/contaCorrente")
    public String contaCorrente(Model model, Principal principal) {
        List<PrimeiraTransferencia> primeiraTransferenciaList = transferenciaService
                .findPrimeiraTransferenciaList(principal.getName());

        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        ContaCorrente contaCorrente = usuario.getContaCorrente();

        model.addAttribute("contaCorrente", contaCorrente);
        model.addAttribute("primeiraTransferenciaList", primeiraTransferenciaList);

        return "contaCorrente";
    }

    @RequestMapping("/contaPoupanca")
    public String contaPoupanca(Model model, Principal principal) {
        List<SalvarTransferencia> salvarTransferenciaList = transferenciaService
                .findSalvarTransferenciaList(principal.getName());
        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        ContaPoupanca contaPoupanca = usuario.getContaPoupanca();

        model.addAttribute("contaPoupanca", contaPoupanca);
        model.addAttribute("salvarTransferenciaList", salvarTransferenciaList);

        return "contaPoupanca";
    }

    @RequestMapping(value = "/deposito", method = RequestMethod.GET)
    public String deposito(Model model) {
        model.addAttribute("tipoConta", "");
        model.addAttribute("valor", "");

        return "deposito";
    }

    @RequestMapping(value = "/deposito", method = RequestMethod.POST)
    public String depositoPOST(@ModelAttribute("valor") String valor,
            @ModelAttribute("tipoConta") String tipoConta, Principal principal) {
        contaService.deposito(tipoConta, Double.parseDouble(valor), principal);

        return "redirect:/bank";
    }

    @RequestMapping(value = "/saque", method = RequestMethod.GET)
    public String saque(Model model) {
        model.addAttribute("tipoConta", "");
        model.addAttribute("valor", "");

        return "saque";
    }

    @RequestMapping(value = "/saque", method = RequestMethod.POST)
    public String saquePOST(@ModelAttribute("valor") String valor,
            @ModelAttribute("tipoConta") String tipoConta, Principal principal) {
        contaService.saque(tipoConta, Double.parseDouble(valor), principal);

        return "redirect:/bank";
    }
}
