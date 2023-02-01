# agendamento
Sistema de agendamento de transferências financeiras, o projeto foi feito em uma arquitetura REST usando a linguagem JAVA e o framework Spring.
O projeto tem 3 endpoints, para cadastrar um agendamento financeiro você irá utilizar o metodo HTTP POST passando os seguintes atributos :

endpoint POST "/agendamentos"

* contaOrigem

* contaDestino

* valorTransferencia

* dataTransferencia

Esses são atributos obrigatórios caso não passe algum deles irá retornar um erro 400 Bad Request.

Para obter acesso aos agendamentos cadastrados no sistema você ira usar o metodo GET que ira retornar os seguintes informações :

endpoint GET "/agendamentos"

* id

* contaOrigem

* contaDestino

* valorTransferencia

* dataTransferencia

* taxa

* dataAgendamento

Caso queira buscar um único recurso acessar o endpoint passando o id :

endpoint GET "/agendamentos/{id}"






