package com.bank.BancoDigital.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bank.BancoDigital.domain.Agendar;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.service.AgendarService;
import com.bank.BancoDigital.service.UsuarioService;

@Controller
@RequestMapping("/agendar")
public class AgendarController {

    @Autowired
    private AgendarService agendarService;

    @Autowired
    private UsuarioService usuarioService;

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAgendar(Model model) {
        Agendar agendar = new Agendar();
        model.addAttribute("agendar", agendar);
        model.addAttribute("dateString", "");

        return "agendar";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String createAgendarPost(@ModelAttribute("agendar") Agendar agendar,
            @ModelAttribute("dateString") String date, Model model, Principal principal) throws ParseException {

        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        Date d1 = format1.parse(date);
        agendar.setDate(d1);

        Usuario usuario = usuarioService.findByUsuario(principal.getName());
        agendar.setUsuario(usuario);

        agendarService.createAgendar(agendar);

        return "redirect:/bank";
    }

}
