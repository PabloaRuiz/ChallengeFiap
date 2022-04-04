# ChallengeFiap

O desafio da equipe será criar uma modelagem e consultas (Java/Hibernate) de informações comportamentais do consumidor no Onboarding
(momento de chegada ao estabelecimento para a retirada do cartão de consumo), exemplos de informações comportamentais que devem ser exibidas na tela 
de onboarding logo após o preenchimento do telefone.

Cenários

1 - Realizar a busca por cliente pelo telefone, 
tem como objetivo trazer informações essenciais. sobre o perfil do cliente.

endpoint:
```sh
/api/v1/clientes/{telefone}
```


2 - Historico de Bebidas e consumo do cliente.

endpoint:
```sh
/api/v1/clientes/historico/bebidas/{id}
```

3 - Historico dos estabelecimentos que o cliente já visitou.

endpoint:
```sh
/api/v1/clientes/historico/estabelecimento/{id}
```

