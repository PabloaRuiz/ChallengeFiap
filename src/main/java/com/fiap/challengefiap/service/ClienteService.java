package com.fiap.challengefiap.service;


import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.repo.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


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
        if(repository.Informativo(telefone) == null || !repository.existsClienteByTelefone(telefone)) {
              throw new RuntimeException("Cliente não encontrado! ");
        }
        return repository.Informativo(telefone);
    }

    public Cliente buscarHistoricoDeEstabelecimentos(Long id) {
        return repository.HistoricoEstabelecimento(id);
    }

    public Cliente buscarHistoricoDeBebidas(Long id) {
        return repository.HistoricoBebidas(id);
    }

}
