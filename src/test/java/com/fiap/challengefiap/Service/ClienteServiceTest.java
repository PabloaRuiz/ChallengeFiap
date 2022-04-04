package com.fiap.challengefiap.Service;


import com.fiap.challengefiap.Entity.Bebida;
import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Entity.Estabelecimento;
import com.fiap.challengefiap.Repository.ClienteRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
public class ClienteServiceTest {

    private ClienteService service;

    @MockBean
    ClienteRepository repository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.service = new ClienteService(repository);
    }

    public Cliente CreateCliente() {
        return new Cliente(null, 1935835634, "Ipa", "Pablo");
    }

    @Test
    @DisplayName("Cadastrar Cliente")
    void salvarCliente() {
        Cliente cliente =  CreateCliente();


        Mockito.when(repository.save(cliente))
                .thenReturn(new Cliente(1l, 1935835634, "Ipa", "Pablo"));


        Cliente clienteSalvo = service.cadastrar(cliente);

        Assertions.assertThat(clienteSalvo.getId()).isNotNull();
        Assertions.assertThat(clienteSalvo.getTelefone()).isEqualTo(1935835634);
        Assertions.assertThat(clienteSalvo.getBebida_Favorita()).isEqualTo("Ipa");
        Assertions.assertThat(clienteSalvo.getCliente()).isEqualTo("Pablo");
    }

    @Test
    @DisplayName("Buscar informações essenciais sobre o cliente e trazer dados da ultima visita")
    void UltimaVisita() throws Exception {
        Long id = 1l;
        Cliente cliente = CreateCliente();
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", LocalDate.now());
        Estabelecimento Bar2 = new Estabelecimento(2l, "Bar2", LocalDate.now().plusDays(4));
        cliente.adicionaEstabelecimento(Bar);
        cliente.adicionaEstabelecimento(Bar2);
        cliente.setId(id);


        Mockito.when(repository.Informativo(cliente.getTelefone()))
                .thenReturn(cliente);

        Cliente ultimaVisita = service.buscar(cliente.getTelefone());

        Assertions.assertThat(ultimaVisita).isNotNull();
        Assertions.assertThat(ultimaVisita.getEstabelecimentos().get(1)).isEqualTo(Bar2);
    }


    @Test
    @DisplayName("Historico de bebidas já consumidas pelo cliente, e total de valor já gasto")
    void HistoricoBebidas() {
        Long id = 1l;
        Cliente cliente = CreateCliente();
        Bebida Cerveja = new Bebida(1l, "Cerveja", new BigDecimal(1.00), new BigDecimal(12.00));
        cliente.adicionaBebidas(Cerveja);
        cliente.setId(id);


        Mockito.when(repository.HistoricoBebidas(id))
                .thenReturn(cliente);

        Cliente historicoBebidas = service.HistoricoBebidas(id);


        Assertions.assertThat(historicoBebidas).isNotNull();
        Assertions.assertThat(cliente.getBebidas().containsAll(historicoBebidas.getBebidas()));
        Assertions.assertThat(historicoBebidas.getBebidas().get(0)).isEqualTo(Cerveja);
        Assertions.assertThat(historicoBebidas.getValorTotal()).isEqualTo(new BigDecimal(12.00).setScale(2, RoundingMode.HALF_EVEN));
    }


    @Test
    @DisplayName("Historico de estabelecimento já visitados pelo cliente")
    void HistoricoEstabelecimento() {
        Long id = 1l;
        Cliente cliente = CreateCliente();
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", LocalDate.now());
        cliente.adicionaEstabelecimento(Bar);
        cliente.setId(id);


        Mockito.when(repository.HistoricoEstabelecimento(id))
                .thenReturn(cliente);

        Cliente historicoEstabelecimento = service.HistoricoEstabelecimento(id);


        Assertions.assertThat(historicoEstabelecimento).isNotNull();
        Assertions.assertThat(cliente.getEstabelecimentos().containsAll(historicoEstabelecimento.getEstabelecimentos()));
        Assertions.assertThat(historicoEstabelecimento.getEstabelecimentos().get(0)).isEqualTo(Bar);
        Assertions.assertThat(historicoEstabelecimento.getEstabelecimentos().get(0).getVisita()).isEqualTo(LocalDate.now());

    }

}
