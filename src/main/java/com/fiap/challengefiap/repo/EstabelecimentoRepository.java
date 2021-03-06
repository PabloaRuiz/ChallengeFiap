package com.fiap.challengefiap.repo;

import com.fiap.challengefiap.entity.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Long> {

}
