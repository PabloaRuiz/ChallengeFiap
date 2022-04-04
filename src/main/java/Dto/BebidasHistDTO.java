package Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challengefiap.Entity.Bebida;
import com.fiap.challengefiap.Entity.Cliente;
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
        return new BebidasHistDTO(cliente.getCliente(),
                cliente.getBebidas(),
                cliente.getValorTotal());
    }

}



