package com.bank.BancoDigital.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.BancoDigital.domain.ContaCorrente;
import com.bank.BancoDigital.domain.Receber;
import com.bank.BancoDigital.domain.ContaPoupanca;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.service.TransferenciaService;
import com.bank.BancoDigital.service.UsuarioService;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

    @Autowired
    private TransferenciaService transferenciaService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/beteenContas", method = RequestMethod.GET)
    public String betweenContas(Model model) {
        model.addAttribute("transferidoDe", "");
        model.addAttribute("transferirPara", "");
        model.addAttribute("valor", "");

        return "betweenContas";

    }

    @RequestMapping(value = "/betweenContas", method = RequestMethod.POST)
    public String betweenContasPost(
            @ModelAttribute("transferidoDe") String transferidoDe,
            @ModelAttribute("transferirPara") String transferirPara,
            @ModelAttribute("valor") String valor,
            Principal principal) throws Exception {
        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        ContaCorrente contaCorrente = usuario.getContaCorrente();
        ContaPoupanca contaPoupanca = usuario.getContaPoupanca();
        transferenciaService.betweenContaTransferencia(transferidoDe, transferirPara, valor, contaCorrente,
                contaPoupanca);

        return "redirect:/bank";

    }

    @RequestMapping(value = "/receber", method = RequestMethod.GET)
    public String receber(Model model, Principal principal) {
        List<Receber> receberList = transferenciaService.findReceberList(principal);

        Receber receber = new Receber();

        model.addAttribute("receberList", receberList);
        model.addAttribute("receber", receber);

        return "receber";
    }

    @RequestMapping(value = "/receber/save", method = RequestMethod.POST)
    public String receberPost(@ModelAttribute("receber") Receber receber, Principal principal) {

        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        receber.setUsuario(usuario);
        transferenciaService.saveReceber(receber);

        return "redirect:/transferencia/receber";
    }

    @RequestMapping(value = "/receber/editar", method = RequestMethod.GET)
    public String receberEditar(@RequestParam(value = "receberNome") String receberNome, Model model,
            Principal principal) {

        Receber receber = transferenciaService.findReceberByNome(receberNome);
        List<Receber> receberList = transferenciaService.findReceberList(principal);

        model.addAttribute("receberList", receberList);
        model.addAttribute("receber", receber);

        return "receber";
    }

    @RequestMapping(value = "/receber/delete", method = RequestMethod.GET)
    @Transactional
    public String recipientDelete(@RequestParam(value = "receberNome") String receberNome, Model model,
            Principal principal) {

        transferenciaService.deleteReceberByNome(receberNome);

        List<Receber> receberList = transferenciaService.findReceberList(principal);

        Receber receber = new Receber();
        model.addAttribute("receber", receber);
        model.addAttribute("receberList", receberList);

        return "receber";
    }

    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.GET)
    public String toSomeoneElse(Model model, Principal principal) {
        List<Receber> receberList = transferenciaService.findReceberList(principal);

        model.addAttribute("receberList", receberList);
        model.addAttribute("tipoConta", "");

        return "toSomeoneElse";
    }

    @RequestMapping(value = "/toSomeoneElse", method = RequestMethod.POST)
    public String toSomeoneElsePost(@ModelAttribute("receberNome") String receberNome,
            @ModelAttribute("tipoConta") String tipoConta, @ModelAttribute("valor") String valor,
            Principal principal) {
        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        Receber receber = transferenciaService.findReceberByNome(receberNome);
        transferenciaService.toSomeoneElseTransferir(receber,
                tipoConta, valor, usuario.getContaCorrente(),
                usuario.getContaPoupanca());

        return "redirect:/bank";

    }

}
