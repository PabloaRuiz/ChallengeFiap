package com.fiap.challengefiap.Controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import Dto.BebidasHistDTO;
import Dto.ClienteInfDTO;
import Dto.EstabelecimentoHistDTO;
import com.fiap.challengefiap.Entity.Bebida;
import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Entity.Estabelecimento;
import com.fiap.challengefiap.Service.ClienteService;
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

    static String Buscar = "/api/v1/clientes";
    static String Historico = "/api/v1/clientes/historico/estabelecimento/";
    static String Historico2 = "/api/v1/clientes/historico/bebidas/";


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
    void BuscarInformacoes() throws Exception {
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", LocalDate.now());
        Bebida cerveja = new Bebida(1l, "Cerveja", new BigDecimal(1.00), new BigDecimal(12.00));
        Cliente cliente = new Cliente(1l, 1935835634, "Ipa", "Pablo");
        cliente.adicionaBebidas(cerveja);
        cliente.adicionaEstabelecimento(Bar);
        ClienteInfDTO clienteDTO = new ClienteInfDTO(1l, "Pablo", "Ipa", null, cliente.getTicketMedio());


        BDDMockito.given(service.buscar(cliente.getTelefone()))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(clienteDTO);


        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Buscar.concat("/1935835634"))
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
    void HistoricoEstabelecimento() throws Exception {
        Estabelecimento Bar = new Estabelecimento(1l, "Bar", null);
        Cliente cliente = new Cliente(1l, 1935835634, "Ipa", "Pablo");
        cliente.adicionaEstabelecimento(Bar);
        EstabelecimentoHistDTO hist = new EstabelecimentoHistDTO();
        hist.setCliente(cliente.getCliente());
        hist.setEstabelecimentos(cliente.getEstabelecimentos());

        BDDMockito.given(service.HistoricoEstabelecimento(cliente.getId()))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(hist);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Historico.concat("/1"))
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
    void HistoricoBedidas() throws Exception {
        Cliente cliente = new Cliente(1l, 1935835634, "Ipa", "Pablo");
        Bebida Ipa = new Bebida(1l,"Ipa", new BigDecimal(1.00), new BigDecimal(12.00));
        Bebida Cerveja = new Bebida(1l,"Cerveja", new BigDecimal(4.00), new BigDecimal(7.00));
        cliente.adicionaBebidas(Ipa);
        cliente.adicionaBebidas(Cerveja);
        BebidasHistDTO bebHist = new BebidasHistDTO();
        bebHist.setCliente(cliente.getCliente());
        bebHist.setBebidas(cliente.getBebidas());
        bebHist.setTotal(cliente.getValorTotal());


        BDDMockito.given(service.HistoricoBebidas(cliente.getId()))
                .willReturn(cliente);

        String json = new ObjectMapper().writeValueAsString(bebHist);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(Historico2.concat("/1"))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.jsonPath("Cliente").value(bebHist.getCliente()))
                .andExpect(MockMvcResultMatchers.jsonPath("Bebidas").isNotEmpty());
    }
}
