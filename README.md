# Desafio proposto no treinamento Java Spring Professional da DevSuperior, referente ao módulo Componentes e Injeção de Dependência, em que era necessário:

## Desenvolver um sistema em Java com Spring Boot para calcular o valor total de um pedido, considerando desconto e frete.

As especificações incluem:

## Cálculo: 
  O valor total é o valor básico do pedido com desconto, mais o frete.

## Regras de Frete:
  Abaixo de R$ 100,00: Frete de R$ 20,00.
  
  De R$ 100,00 a R$ 200,00 (exclusivo): Frete de R$ 12,00.
  
  R$ 200,00 ou mais: Frete Grátis.

## Estrutura:
  Um pedido deve ser representado por um objeto Order com code (Integer), basic (Double) e discount (Double).

### Componentes/Serviços: A lógica de cálculo deve ser dividida em:
  OrderService: Responsável por operações relacionadas a pedidos, com o método total(order: Order): double.
  
  ShippingService: Responsável por operações relacionadas a frete, com o método shipment(order: Order): double.

## Implementação: 
  Em Java com Spring Boot, utilizando a anotação @Service para registrar os componentes. A saída deve ser exibida no log do terminal.
