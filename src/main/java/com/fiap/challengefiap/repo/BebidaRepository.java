package com.fiap.challengefiap.repo;

import com.fiap.challengefiap.entity.Bebida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BebidaRepository extends JpaRepository<Bebida, Long> {

}
