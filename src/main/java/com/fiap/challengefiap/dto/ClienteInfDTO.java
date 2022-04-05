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
    private String Nome;

    @JsonProperty("Estilo Preferido")
    private String Bebida_Favorita;

    @JsonProperty("Data da ultima visita")
    private LocalDate Ultima_visita;

    @JsonProperty("Ticket médio")
    private BigDecimal Ticket;


    public ClienteInfDTO transformar(Cliente cliente) {
        return new ClienteInfDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getBebida_Favorita(),
                cliente.getEstabelecimentos().get(0).getVisita(),
                cliente.getCalcularTicketMedio());
    }

}
