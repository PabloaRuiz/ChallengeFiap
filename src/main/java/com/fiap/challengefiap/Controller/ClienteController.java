package com.fiap.challengefiap.Controller;


import Dto.BebidasHistDTO;
import Dto.ClienteInfDTO;
import Dto.EstabelecimentoHistDTO;
import com.fiap.challengefiap.Entity.Cliente;
import com.fiap.challengefiap.Service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api/v1/clientes")
public class ClienteController {

    private ClienteService service;


    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }


    @GetMapping("{telefone}")
    @ResponseStatus(HttpStatus.FOUND)
    public ClienteInfDTO buscar(@PathVariable Integer telefone){
        if (telefone == null)  {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
         Cliente obj = service.buscar(telefone);
         ClienteInfDTO clientesDto = new ClienteInfDTO().transformar(obj);
         return clientesDto;
    }

    @GetMapping("/historico/estabelecimento/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public EstabelecimentoHistDTO Historico(@PathVariable Long id) {
        Cliente obj = service.HistoricoEstabelecimento(id);
        EstabelecimentoHistDTO clientDto = new EstabelecimentoHistDTO().transformar(obj);
        return clientDto;

    }

    @GetMapping("/historico/bebidas/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BebidasHistDTO HistoricoBebidas(@PathVariable Long id) {
        Cliente obj = service.HistoricoBebidas(id);
        BebidasHistDTO clientDto = new BebidasHistDTO().transformarB(obj);
        return clientDto;
    }
}
