package com.fiap.challengefiap.service;

import com.fiap.challengefiap.entity.Estabelecimento;
import com.fiap.challengefiap.repo.EstabelecimentoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;

@ExtendWith(SpringExtension.class)
public class EstabelecimentoServiceTest {

    private EstabelecimentoService service;

    @MockBean
    EstabelecimentoRepository repository;

    @BeforeEach
    public void beforeEach() {
        MockitoAnnotations.initMocks(this);
        this.service = new EstabelecimentoService(repository);
    }

    private Estabelecimento CreateEstabelecimento() {
        return new Estabelecimento(null, "Bar", LocalDate.now());
    }

    @Test
    @DisplayName("Cadastrar estabelecimento")
    void cadastrarEstabelecimento() {
        Estabelecimento estabelecimento = CreateEstabelecimento();

        Mockito.when(repository.save(estabelecimento))
                .thenReturn(new Estabelecimento(1l, "Bar", LocalDate.now()));


        Estabelecimento estabelecimentoSalvo = service.cadastrar(estabelecimento);

        Assertions.assertThat(estabelecimentoSalvo.getId()).isNotNull();
        Assertions.assertThat(estabelecimento.getNomeEstabelecimento()).isEqualTo("Bar");
        Assertions.assertThat(estabelecimento.getVisita()).isEqualTo(LocalDate.now());
    }
}
