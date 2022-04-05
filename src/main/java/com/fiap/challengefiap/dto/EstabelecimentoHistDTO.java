package com.fiap.challengefiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.entity.Estabelecimento;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstabelecimentoHistDTO {


    @JsonProperty("Cliente")
    private String cliente;

    @JsonProperty("Estabelecimentos")
    private List<Estabelecimento> estabelecimentos;

    public EstabelecimentoHistDTO transformar(Cliente cliente) {
        return new EstabelecimentoHistDTO(cliente.getNome(), cliente.getEstabelecimentos());
    }
}
