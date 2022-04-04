package com.fiap.challengefiap.Service;

import com.fiap.challengefiap.Entity.Bebida;
import com.fiap.challengefiap.Repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
