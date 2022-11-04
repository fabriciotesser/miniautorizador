# Desafio VR Benefícios

### A aplicação miniautorizador foi desenvolvida utilizando as seguintes tecnologias:

|TECNOLOGIA      |VERSÃO     |
|----------------|-----------|
|Spring Boot     |      2.7.5|
|Java            |        1.8|
|Mysql           |        5.7|
|Docker-compose  |        3.7|

Procurei desenvolver a aplicação, tentando ao máximo deixá-la simples, porém funcional. Respeitando todos os requisitos sugeridos na documentação enviada.

Elaborei testes de integração automatizados, englobando todas as possibilidades que a aplicação suporta, cobrindo a aplicação quase por completo.

Sobre o endpoint "/transacoes", como na documentação não falava sobre a possibilidade de ter uma transação onde gerasse crédito para o cartão, assumi que todas as transações irão debitar, dessa forma está fixo dentro do service a negação do valor. Mas no caso eu poderia ter criado 2 endpoints, um para credito e outro para debito.

Com relação ao saldo, optei por fazer o cálculo dentro do banco de dados, pois otimiza memória (não precisaria trazer todas as transações para um ArrayList) e deixa o processo bem mais leve.

#### Queria agradecer ao time VR Benefícios que independente do resultado, gostei muito da forma como procederam com o teste! foi bem divertido! Desejo o melhor para todos vocês!!!
