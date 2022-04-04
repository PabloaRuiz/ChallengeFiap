package com.fiap.challengefiap.Repository;

import com.fiap.challengefiap.Entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {


    @Query(value = "select * from tb_clientes as A " +
            " inner Join tb_estabelecimentos_cliente B on A.cliente_id = B.cliente_id " +
            " inner join tb_estabelecimento as C on C.estabelecimento_id = B.estabelecimento_id " +
            " WHERE A.telefone = :telefone " +
            " Order by C.visita desc limit 1",
           nativeQuery = true)
    Cliente Informativo(Integer telefone);

    @Query(value = "select * from tb_clientes as A " +
            " inner join tb_estabelecimentos_cliente as B " +
            " on A.cliente_id = B.cliente_id " +
            " inner join tb_estabelecimento as C " +
            " on B.estabelecimento_id = C.estabelecimento_id " +
            " where A.cliente_id = :id",
            nativeQuery = true)
    Cliente HistoricoEstabelecimento(Long id);


    @Query(value = "select * from tb_clientes as A " +
            " inner join tb_bebidas_cliente as B " +
            " on A.cliente_id = B.cliente_id " +
            " inner join tb_bebidas as C " +
            " on B.bebida_id = C.bebida_id " +
            " where A.cliente_id = :id",
            nativeQuery = true)
    Cliente HistoricoBebidas(Long id);

}
