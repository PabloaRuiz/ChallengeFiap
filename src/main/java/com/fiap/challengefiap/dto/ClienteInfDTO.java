package com.fiap.challengefiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challengefiap.entity.Cliente;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;


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


    public ClienteInfDTO transformar(Cliente cliente) {
        return new ClienteInfDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getcervejaFavorita(),
                cliente.getEstabelecimentos().get(0).getVisita(),
                cliente.getCalcularTicketMedio());
    }

}
