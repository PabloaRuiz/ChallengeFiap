package com.fiap.challengefiap.Repository;


import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Entity.Estabelecimento;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class ClienteRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ClienteRepository repository;


    @Test
    @DisplayName("Deve salvar um cliente")
    void cadastraCliente() {
        Cliente cliente = new Cliente(null, 1935835634, "Ipa", "Pablo");

        Cliente clienteCadastrado = repository.save(cliente);

        Assertions.assertThat(clienteCadastrado.getId()).isNotNull();
        Assertions.assertThat(clienteCadastrado.getCliente()).isEqualTo(cliente.getCliente());
        Assertions.assertThat(clienteCadastrado.getTelefone()).isEqualTo(cliente.getTelefone());

    }


    @Test
    @DisplayName("Deve trazer informações do cliente e a ultima visita realizada em algum estabelecimento")
    void UltimaVisita() {
        Cliente cliente = new Cliente(null, 1935835634, "Ipa", "Pablo");
        Estabelecimento Bar = new Estabelecimento(null, "Bar", LocalDate.now());
        Estabelecimento Bar2 = new Estabelecimento(null, "Bar", LocalDate.now().plusDays(6));
        cliente.adicionaEstabelecimento(Bar2);
        cliente.adicionaEstabelecimento(Bar);


        entityManager.persist(Bar);
        entityManager.persist(Bar2);
        entityManager.persist(cliente);

        Cliente ultimaVisita = repository.Informativo(1935835634);


        Assertions.assertThat(ultimaVisita).isNotNull();
        Assertions.assertThat(ultimaVisita.getEstabelecimentos().get(0)).isEqualTo(Bar2);
        Assertions.assertThat(ultimaVisita.getBebida_Favorita()).isEqualTo(cliente.getBebida_Favorita());
    }

}
