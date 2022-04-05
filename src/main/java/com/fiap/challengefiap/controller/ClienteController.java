package com.fiap.challengefiap.controller;


import com.fiap.challengefiap.dto.BebidasHistDTO;
import com.fiap.challengefiap.dto.ClienteInfDTO;
import com.fiap.challengefiap.dto.EstabelecimentoHistDTO;
import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
    public ClienteInfDTO buscarClientePorTelefone(@PathVariable Integer telefone){
         Cliente obj = service.buscar(telefone);
         ClienteInfDTO clientesDto = new ClienteInfDTO().transformar(obj);
         return clientesDto;
    }

    @GetMapping("/historico/estabelecimento/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public EstabelecimentoHistDTO buscarHistoricoDeEstabelecimentosPorId(@PathVariable Long id) {
        Cliente obj = service.buscarHistoricoDeEstabelecimentos(id);
        EstabelecimentoHistDTO clientDto = new EstabelecimentoHistDTO().transformar(obj);
        return clientDto;

    }

    @GetMapping("/historico/bebidas/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public BebidasHistDTO buscarHistoricoDeBebidasPorId(@PathVariable Long id) {
        Cliente obj = service.buscarHistoricoDeBebidas(id);
        BebidasHistDTO clientDto = new BebidasHistDTO().transformarB(obj);
        return clientDto;
    }
}
