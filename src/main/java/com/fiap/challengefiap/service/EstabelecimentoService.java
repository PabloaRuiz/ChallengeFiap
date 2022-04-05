package com.fiap.challengefiap.service;



import com.fiap.challengefiap.entity.Estabelecimento;
import com.fiap.challengefiap.repo.EstabelecimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstabelecimentoService {

    private EstabelecimentoRepository repository;

    @Autowired
    public EstabelecimentoService(EstabelecimentoRepository repository) {
        this.repository = repository;
    }

    public Estabelecimento cadastrar(Estabelecimento estabelecimento) {
        return repository.save(estabelecimento);
    }



}
