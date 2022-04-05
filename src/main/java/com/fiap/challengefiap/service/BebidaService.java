package com.fiap.challengefiap.service;

import com.fiap.challengefiap.entity.Bebida;
import com.fiap.challengefiap.repo.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class BebidaService {

    private BebidaRepository repository;

    @Autowired
    public BebidaService(BebidaRepository repository) {
        this.repository = repository;
    }

    public Bebida cadastrar(Bebida bebida) {
        return repository.save(bebida);
    }

}
