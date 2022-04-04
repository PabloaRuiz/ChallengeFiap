package com.fiap.challengefiap.Service;


import com.fiap.challengefiap.Entity.Bebida;
import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Repository.BebidaRepository;
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

@ExtendWith(SpringExtension.class)
public class BebidaServiceTest {

    private BebidaService service;

    @MockBean
    private BebidaRepository repository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.service = new BebidaService(repository);
    }

    private Bebida CreateBebida() {
        return new Bebida(null, "Cerveja", new BigDecimal(1.00), new BigDecimal(12.00));
    }


    @Test
    @DisplayName("Cadastrar uma Bebida")
    void salvarBebida() {
        Bebida bebida = CreateBebida();


        Mockito.when(repository.save(bebida))
                .thenReturn(new Bebida(1l, "Cerveja", new BigDecimal(1.00), new BigDecimal(12.00)));


        Bebida bebidaSalva = service.cadastrar(bebida);

        Assertions.assertThat(bebidaSalva.getId()).isNotNull();
        Assertions.assertThat(bebidaSalva.getTipo_Cerveja()).isEqualTo("Cerveja");
        Assertions.assertThat(bebidaSalva.getConsumo()).isEqualTo(new BigDecimal(1.00));
        Assertions.assertThat(bebidaSalva.getValor()).isEqualTo(new BigDecimal(12.00));

    }
}
