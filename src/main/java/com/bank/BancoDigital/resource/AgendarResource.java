package com.bank.BancoDigital.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.BancoDigital.domain.Agendar;
import com.bank.BancoDigital.service.AgendarService;

@RestController
@RequestMapping("/api/agendar")
@PreAuthorize("hasFuncao('ADMIN')")
public class AgendarResource {

    @Autowired
    private AgendarService agendarService;

    @RequestMapping("/all")
    public List<Agendar> findAgendarList() {
        List<Agendar> agendarList = agendarService.findAll();

        return agendarList;
    }

    @RequestMapping("/{id}/confirmar")
    public void confirmarAgendar(@PathVariable("id") Long id) {
        agendarService.confirmarAgendar(id);
    }
}
