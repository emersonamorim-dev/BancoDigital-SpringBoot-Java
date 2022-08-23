package com.bank.BancoDigital.service.UsuarioServiceImpl;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.BancoDigital.dao.ContaCorrenteDao;
import com.bank.BancoDigital.dao.PrimeiraTransferenciaDao;
import com.bank.BancoDigital.dao.ReceberDao;
import com.bank.BancoDigital.dao.ContaPoupancaDao;
import com.bank.BancoDigital.dao.SalvarTransferenciaDao;
import com.bank.BancoDigital.domain.ContaCorrente;
import com.bank.BancoDigital.domain.PrimeiraTransferencia;
import com.bank.BancoDigital.domain.Receber;
import com.bank.BancoDigital.domain.ContaPoupanca;
import com.bank.BancoDigital.domain.SalvarTransferencia;
import com.bank.BancoDigital.domain.Usuario;
import com.bank.BancoDigital.service.TransferenciaService;
import com.bank.BancoDigital.service.UsuarioService;

@Service
public class TransferenciaServiceImpl implements TransferenciaService {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrimeiraTransferenciaDao primeiraTransferenciaDao;

    @Autowired
    private SalvarTransferenciaDao salvarTransferenciaDao;

    @Autowired
    private ContaCorrenteDao contaCorrenteDao;

    @Autowired
    private ContaPoupancaDao contaPoupancaDao;

    private ReceberDao receberDao;

    public List<PrimeiraTransferencia> findPrimeiraTransferenciaList(String usuario) {
        Usuario user = usuarioService.findByUsuario(usuario);
        List<PrimeiraTransferencia> primeiraTransferenciaList = user.getContaCorrente().getPrimeiraTransferenciaList();

        return primeiraTransferenciaList;
    }

    public List<SalvarTransferencia> findSalvarTransferenciaList(String usuario) {
        Usuario user = usuarioService.findByUsuario(usuario);
        List<SalvarTransferencia> salvarTransferenciaList = user.getContaPoupanca().getSalvarTransferenciaList();

        return salvarTransferenciaList;
    }

    public void savePrimeiroDepositoTransferido(PrimeiraTransferencia primeiraTransferencia) {
        primeiraTransferenciaDao.save(primeiraTransferencia);
    }

    public void saveSalvarDepositoTransferido(SalvarTransferencia salvarTransferencia) {
        salvarTransferenciaDao.save(salvarTransferencia);
    }

    public void savePrimeiroSaqueTransferido(PrimeiraTransferencia primeiraTransferencia) {
        primeiraTransferenciaDao.save(primeiraTransferencia);
    }

    public void saveSalvarSaqueTransferido(SalvarTransferencia salvarTransferencia) {
        salvarTransferenciaDao.save(salvarTransferencia);
    }

    @Override
    public void betweenContaTransferencia(String transferenciaDe, String transferenciaPara, String valor,
            ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) throws Exception {
        if (transferenciaDe.equalsIgnoreCase("Primeiro") && transferenciaDe.equalsIgnoreCase("Salvar")) {
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().subtract(new BigDecimal(valor)));
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().add(new BigDecimal(valor)));
            contaCorrenteDao.save(contaCorrente);
            contaPoupancaDao.save(contaPoupanca);

            Date date = new Date();

            PrimeiraTransferencia primeiraTransferencia = new PrimeiraTransferencia(date,
                    "Entre transferência de conta de " + transferenciaDe + " para " + transferenciaPara, "Conta",
                    "Finalizado",
                    Double.parseDouble(valor), contaCorrente.getSaldoConta(), contaCorrente);
            primeiraTransferenciaDao.save(primeiraTransferencia);
        } else if (transferenciaDe.equalsIgnoreCase("Salvar") && transferenciaPara.equalsIgnoreCase("Primeira")) {
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().add(new BigDecimal(valor)));
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().subtract(new BigDecimal(valor)));
            contaCorrenteDao.save(contaCorrente);
            contaPoupancaDao.save(contaPoupanca);

            Date date = new Date();

            SalvarTransferencia salvarTransferencia = new SalvarTransferencia(date,
                    "Entre transferência de conta de " + transferenciaDe + " para " + transferenciaPara, "Transferir",
                    "Finilizado",
                    Double.parseDouble(valor), contaCorrente.getSaldoConta(), contaPoupanca);
            salvarTransferenciaDao.save(salvarTransferencia);
        } else {
            throw new Exception("Transferência inválida");
        }
    }

    public List<Receber> findReceberList(Principal principal) {
        String usuario = principal.getName();
        List<Receber> receberList = receberDao.findAll().stream()
                .filter(receber -> usuario.equals(receber.getUsuario().getUsername()))

                .collect(Collectors.toList());

        return receberList;
    }

    @Override
    public Receber saveReceber(Receber receber) {
        return receberDao.save(receber);
    }

    @Override
    public Receber findReceberByNome(String receberNome) {
        return receberDao.findByNome(receberNome);
    }

    @Override
    public void deleteReceberByNome(String receberNome) {
        receberDao.deleteByNome(receberNome);

    }

    @Override
    public void toSomeoneElseTransferir(Receber receber, String tipoConta, String valor,
            ContaCorrente contaCorrente, ContaPoupanca contaPoupanca) {
        if (tipoConta.equalsIgnoreCase("Primeira")) {
            contaCorrente.setSaldoConta(contaCorrente.getSaldoConta().subtract(new BigDecimal(valor)));
            contaCorrenteDao.save(contaCorrente);

            Date date = new Date();

            PrimeiraTransferencia primeiraTransferencia = new PrimeiraTransferencia(date,
                    "Transferir para o destinatário " + receber.getNome(), "Transferencia", "Finalizado",
                    Double.parseDouble(valor),
                    contaCorrente.getSaldoConta(), contaCorrente);
            primeiraTransferenciaDao.save(primeiraTransferencia);
        } else if (tipoConta.equalsIgnoreCase("Salvar")) {
            contaPoupanca.setSaldoConta(contaPoupanca.getSaldoConta().subtract(new BigDecimal(valor)));
            contaPoupancaDao.save(contaPoupanca);

            Date date = new Date();

            SalvarTransferencia salvarTransferencia = new SalvarTransferencia(date,
                    "Transferir para o destinatário " + receber.getNome(), "Transferir", "Finalizado",
                    Double.parseDouble(valor),
                    contaPoupanca.getSaldoConta(), contaPoupanca);
            salvarTransferenciaDao.save(salvarTransferencia);

        }
    }

}
