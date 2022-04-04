package Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Entity.Estabelecimento;
import lombok.*;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EstabelecimentoHistDTO {


    @JsonProperty("Cliente")
    private String Cliente;

    @JsonProperty("Estabelecimentos")
    private List<Estabelecimento> estabelecimentos;

    public EstabelecimentoHistDTO transformar(Cliente cliente) {
        return new EstabelecimentoHistDTO(cliente.getCliente(), cliente.getEstabelecimentos());
    }
}
