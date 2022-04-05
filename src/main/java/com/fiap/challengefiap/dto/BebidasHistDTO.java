package com.fiap.challengefiap.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challengefiap.entity.Bebida;
import com.fiap.challengefiap.entity.Cliente;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class BebidasHistDTO {


     @JsonProperty("Cliente")
     private String Cliente;


     @JsonProperty("Bebidas")
     private List<Bebida> bebidas;

    @JsonProperty("Total Consumido")
    private BigDecimal Total;


    public BebidasHistDTO transformarB(Cliente cliente) {
        return new BebidasHistDTO(cliente.getNome(),
                cliente.getBebidas(),
                cliente.getCalculoTotal());
    }

}



