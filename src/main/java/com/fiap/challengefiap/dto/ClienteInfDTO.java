package com.fiap.challengefiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.entity.enums.Classificacao;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClienteInfDTO {

    @JsonProperty("Cadastro")
    private Long Id;

    @JsonProperty("Cliente")
    private String nome;

    @JsonProperty("Estilo Preferido")
    private String cervejaFavorita;

    @JsonProperty("Data da ultima visita")
    private LocalDate ultimaVisita;

    @JsonProperty("Ticket médio")
    private BigDecimal ticket;

    @JsonProperty("Classificacao")
    private Classificacao classificacao;



    public ClienteInfDTO transformar(Cliente cliente) {
        return new ClienteInfDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getcervejaFavorita(),
                cliente.getEstabelecimentos().get(0).getVisita(),
                cliente.getCalcularTicketMedio(),
                cliente.getClassificacao());
    }

}
