package com.bank.BancoDigital.service.UsuarioServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.BancoDigital.dao.AgendarDao;
import com.bank.BancoDigital.domain.Agendar;
import com.bank.BancoDigital.service.AgendarService;

@Service
public class AgendarServiceImpl implements AgendarService {

    @Autowired
    private AgendarDao agendarDao;

    public Agendar createAgendar(Agendar agendar) {
        return agendarDao.save(agendar);
    }

    public List<Agendar> findAll() {
        return agendarDao.findAll();
    }

    public Agendar findAgendar(Long id) {
        return agendarDao.findOne(id);
    }

    @Override
    public void confirmarAgendar(Long id) {
        Agendar agendar = findAgendar(id);
        agendar.setConfirmado(true);
        agendarDao.save(agendar);

    }
}
