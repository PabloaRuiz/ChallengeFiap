package com.fiap.challengefiap.controller;


import com.fiap.challengefiap.dto.BebidasHistDTO;
import com.fiap.challengefiap.dto.ClienteInfDTO;
import com.fiap.challengefiap.dto.EstabelecimentoHistDTO;
import com.fiap.challengefiap.entity.Cliente;
import com.fiap.challengefiap.service.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/clientes")
@Api("Challenge Fiap")
public class ClienteController {

    private ClienteService service;


    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }


    @GetMapping("{telefone}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Informações gerenciais do cliente, busca realizada pelo telefone do cliente.")
    public ClienteInfDTO buscarClientePorTelefone(@PathVariable Integer telefone){
        Cliente obj = service.buscar(telefone);
         ClienteInfDTO clientesDto = new ClienteInfDTO().transformar(obj);
         return clientesDto;
    }

    @GetMapping("/historico/estabelecimento/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Traz historico de todos os estabelcimentos que o cliente já visitou.")
    public EstabelecimentoHistDTO buscarHistoricoDeEstabelecimentosPorId(@PathVariable Long id) {
        Cliente obj = service.buscarHistoricoDeEstabelecimentos(id);
        EstabelecimentoHistDTO clientDto = new EstabelecimentoHistDTO().transformar(obj);
        return clientDto;

    }

    @GetMapping("/historico/bebidas/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation("Traz historico de todas as bebidas que o cliente já utilizou")
    public BebidasHistDTO buscarHistoricoDeBebidasPorId(@PathVariable Long id) {
        Cliente obj = service.buscarHistoricoDeBebidas(id);
        BebidasHistDTO clientDto = new BebidasHistDTO().transformarB(obj);
        return clientDto;
    }
}
