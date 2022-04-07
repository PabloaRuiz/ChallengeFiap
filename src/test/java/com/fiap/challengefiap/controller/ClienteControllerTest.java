package com.fiap.challengefiap.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fiap.challengefiap.dto.BebidasHistDTO;
import com.fiap.challengefiap.dto.ClienteInfDTO;
import com.fiap.challengefiap.dto.EstabelecimentoHistDTO;
import com.fiap.challengefiap.entity.Bebida;
import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.entity.Estabelecimento;
import com.fiap.challengefiap.entity.enums.Classificacao;
import com.fiap.challengefiap.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;
import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ClienteController.class)
@AutoConfigureMockMvc
public class ClienteControllerTest {

    static String BUSCAR = "/api/v1/clientes";
    static String HISTORICO = "/api/v1/clientes/historico/estabelecimento/";
    static String HISTORICO_2 = "/api/v1/clientes/historico/bebidas/";


    @Autowired
    MockMvc mvc;

    @MockBean
    private ClienteService service;

    private ClienteController controller;


    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.controller = new ClienteController(service);
    }


    @Test
    @DisplayName("Busca informações gerai de um determino cliente")
    void buscarInformacoes() throws Exception {
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", LocalDate.now());
        Bebida cerveja = new Bebida(1l, "Cerveja", new BigDecimal(1.00), new BigDecimal(12.00));
        Cliente cliente = new Cliente(1l, 1935835634, "Ipa", "Pablo");
        cliente.adicionaBebidas(cerveja);
        cliente.adicionaEstabelecimento(Bar);
        ClienteInfDTO clienteDTO = new ClienteInfDTO(1l, "Pablo", "Ipa", null, cliente.getCalcularTicketMedio(), cliente.raking() );


        BDDMockito.given(service.buscar(cliente.getTelefone()))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(clienteDTO);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BUSCAR.concat("/1935835634"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("Cadastro").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("Cliente").value(clienteDTO.getNome()));
    }

    @Test
    @DisplayName("Traz o historico de estabelecimento que o cliente já frenquentou")
    void buscarHistoricoEstabelecimento() throws Exception {
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", null);
        Cliente cliente = new Cliente(1l, 1935835634, "Ipa", "Pablo");
        cliente.adicionaEstabelecimento(Bar);
        EstabelecimentoHistDTO hist = new EstabelecimentoHistDTO();
        hist.setCliente(cliente.getNome());
        hist.setEstabelecimentos(cliente.getEstabelecimentos());

        BDDMockito.given(service.buscarHistoricoDeBebidas(cliente.getId()))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(hist);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HISTORICO.concat("/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("Cliente").value(hist.getCliente()))
                .andExpect(MockMvcResultMatchers.jsonPath("Estabelecimentos").isNotEmpty());
    }

    @Test
    @DisplayName("Traz o historico de Bebidas e o valor total já gasto pelo cliente")
    void buscarHistoricoBedidas() throws Exception {
        Cliente cliente = new Cliente(1l, 1935835634, "Ipa", "Pablo");
        Bebida Ipa = new Bebida(1l,"Ipa", new BigDecimal(1.00), new BigDecimal(12.00));
        Bebida Cerveja = new Bebida(1l,"Cerveja", new BigDecimal(4.00), new BigDecimal(7.00));
        cliente.adicionaBebidas(Ipa);
        cliente.adicionaBebidas(Cerveja);
        BebidasHistDTO bebHist = new BebidasHistDTO();
        bebHist.setCliente(cliente.getNome());
        bebHist.setBebidas(cliente.getBebidas());
        bebHist.setTotal(cliente.getCalculoTotal());


        BDDMockito.given(service.buscarHistoricoDeBebidas(cliente.getId()))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(bebHist);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(HISTORICO_2.concat("/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("Cliente").value(bebHist.getCliente()))
                .andExpect(MockMvcResultMatchers.jsonPath("Bebidas").isNotEmpty());
    }
}
