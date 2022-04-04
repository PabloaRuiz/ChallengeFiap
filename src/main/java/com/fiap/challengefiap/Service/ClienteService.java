package com.fiap.challengefiap.Service;


import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Repository.ClienteRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;


@Service
public class ClienteService {

    private ClienteRepository repository;

    @Autowired
    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public Cliente cadastrar(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente buscar(Integer telefone) {
        return repository.Informativo(telefone);
    }

    public Cliente HistoricoEstabelecimento(Long id) {
        return repository.HistoricoEstabelecimento(id);
    }

    public Cliente HistoricoBebidas(Long id) {
        return repository.HistoricoBebidas(id);
    }

}
