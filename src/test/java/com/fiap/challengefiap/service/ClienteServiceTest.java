package com.fiap.challengefiap.service;


import com.fiap.challengefiap.entity.Bebida;
import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.entity.Estabelecimento;
import com.fiap.challengefiap.repo.ClienteRepository;
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
    void cadastrarCliente() {
        Cliente cliente =  CreateCliente();


        Mockito.when(repository.save(cliente))
                .thenReturn(new Cliente(1l, 1935835634, "Ipa", "Pablo"));


        Cliente clienteSalvo = service.cadastrar(cliente);

        Assertions.assertThat(clienteSalvo.getId()).isNotNull();
        Assertions.assertThat(clienteSalvo.getTelefone()).isEqualTo(1935835634);
        Assertions.assertThat(clienteSalvo.getBebida_Favorita()).isEqualTo("Ipa");
        Assertions.assertThat(clienteSalvo.getNome()).isEqualTo("Pablo");
    }

    @Test
    @DisplayName("Buscar informações essenciais sobre o cliente e trazer dados da ultima visita")
    void buscarInformacoesDoCliente() throws Exception {
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
    void buscarHistoricoDeBebidas() {
        Long id = 1l;
        Cliente cliente = CreateCliente();
        Bebida Cerveja = new Bebida(1l, "Cerveja", new BigDecimal(1.00), new BigDecimal(12.00));
        cliente.adicionaBebidas(Cerveja);
        cliente.setId(id);


        Mockito.when(repository.HistoricoBebidas(id))
                .thenReturn(cliente);

        Cliente historicoBebidas = service.buscarHistoricoDeBebidas(id);


        Assertions.assertThat(historicoBebidas).isNotNull();
        Assertions.assertThat(cliente.getBebidas().containsAll(historicoBebidas.getBebidas()));
        Assertions.assertThat(historicoBebidas.getBebidas().get(0)).isEqualTo(Cerveja);
        Assertions.assertThat(historicoBebidas.getCalculoTotal()).isEqualTo(new BigDecimal(12.00).setScale(2, RoundingMode.HALF_EVEN));
    }


    @Test
    @DisplayName("Historico de estabelecimento já visitados pelo cliente")
    void buscarHistoricoDeEstabelecimento() {
        Long id = 1l;
        Cliente cliente = CreateCliente();
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", LocalDate.now());
        cliente.adicionaEstabelecimento(Bar);
        cliente.setId(id);


        Mockito.when(repository.HistoricoEstabelecimento(id))
                .thenReturn(cliente);

        Cliente historicoEstabelecimento = service.buscarHistoricoDeEstabelecimentos(id);


        Assertions.assertThat(historicoEstabelecimento).isNotNull();
        Assertions.assertThat(cliente.getEstabelecimentos().containsAll(historicoEstabelecimento.getEstabelecimentos()));
        Assertions.assertThat(historicoEstabelecimento.getEstabelecimentos().get(0)).isEqualTo(Bar);
        Assertions.assertThat(historicoEstabelecimento.getEstabelecimentos().get(0).getVisita()).isEqualTo(LocalDate.now());

    }

}
