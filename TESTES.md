# Guia de Testes

Documentação consolidada da suíte de testes do projeto.

## Visão Geral
- **Total de testes:** 41
- **Categorias:**
  - Entidade: `OrderTest` (6)
  - Serviço (unitário, com Mockito): `OrderServiceTest` (11)
  - Serviço de frete: `ShippingServiceTest` (10)
  - Integração (Spring completo): `OrderServiceIntegrationTest` (11)
  - Contexto da aplicação: `SistemaCalculoValorTotalPedidosApplicationTests` (3)
- **Ambiente:** Java 21, Spring Boot 3.5.4, JUnit 5, Mockito
- **Perfil de testes:** `test` (evita executar o `CommandLineRunner` durante os testes)

## Como Rodar

```bash
# Todos os testes (recomendado)
./mvnw test

# Teste(s) específico(s)
./mvnw test -Dtest=OrderServiceTest
./mvnw test -Dtest=OrderServiceTest#shouldCalculateTotalCase1
```

> Se precisar, dê permissão ao wrapper: `chmod +x mvnw`

## O que é coberto
- **Cálculo de desconto:** 0%, percentuais fracionários, 100%
- **Cálculo de frete:** faixas <100, entre 100–200, ≥200, valores de fronteira e pedidos grandes
- **Cálculo total:** `(básico - desconto) + frete`, ordem das operações, precisão decimal
- **Integração:** componentes reais do Spring, injeção de dependência e contexto

## Tempo de execução
A suíte completa leva ~2–3 segundos em ambiente local com Java 21.
