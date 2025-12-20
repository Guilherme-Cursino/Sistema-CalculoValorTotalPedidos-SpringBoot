# Sistema de Cálculo de Valor Total de Pedidos

Sistema desenvolvido em Spring Boot para calcular o valor total de pedidos, aplicando descontos e calculando fretes de acordo com regras de negócio específicas.

## Sobre o Projeto

Este sistema processa pedidos aplicando as seguintes lógicas:
- **Cálculo de desconto**: Aplica percentual de desconto sobre o valor básico do pedido
- **Cálculo de frete**: Determina o custo de envio baseado no valor do pedido
- **Valor total**: Combina valor com desconto aplicado + frete calculado

### Regras de Negócio

#### Cálculo do Valor Final
```
Valor Total = (Valor Básico - Desconto) + Frete
```
#### Regras de Frete

| Valor do Pedido | Custo do Frete |
|----------------|----------------|
| Abaixo de R$ 100,00 | R$ 20,00 |
| Entre R$ 100,00 e R$ 200,00 | R$ 12,00 |
| R$ 200,00 ou mais | Grátis |

## Tecnologias Utilizadas

- **Java 21** - Linguagem de programação
- **Spring Boot 3.5.4** - Framework para desenvolvimento de aplicações Java
- **Maven** - Gerenciador de dependências e build
- **JUnit 5** - Framework de testes

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/cursino/guilherme/sistemacalculovalortotalpedidos/
│   │   ├── SistemaCalculoValorTotalPedidosApplication.java  # Classe principal
│   │   ├── entities/
│   │   │   └── Order.java                                    # Entidade de pedido
│   │   └── services/
│   │       ├── OrderService.java                             # Serviço de pedidos
│   │       └── ShippingService.java                          # Serviço de frete
│   └── resources/
│       └── application.properties                            # Configurações da aplicação
└── test/
    └── java/cursino/guilherme/sistemacalculovalortotalpedidos/
        └── SistemaCalculoValorTotalPedidosApplicationTests.java  # Testes
```

## Modelo de Dados

### Order (Pedido)

| Atributo | Tipo | Descrição |
|----------|------|-----------|
| `code` | Integer | Código identificador do pedido |
| `basic` | Double | Valor básico do pedido |
| `discount` | Double | Percentual de desconto (0-100) |

## Componentes e Serviços

### OrderService
Responsável pela lógica de cálculo do valor total do pedido.

**Método principal:**
- `total(Order order): Double` - Calcula o valor total aplicando desconto e frete

### ShippingService
Responsável pela lógica de cálculo do frete.

**Método principal:**
- `shipment(Order order): Double` - Determina o valor do frete baseado no valor do pedido

## Como Executar

### Pré-requisitos

- Java 21 ou superior
- Maven 3.6+ (ou utilizar o Maven Wrapper incluído no projeto)

### Passos para execução

1. **Clone o repositório**
```bash
git clone https://github.com/Guilherme-Cursino/Sistema-CalculoValorTotalPedidos-SpringBoot.git
cd Sistema-CalculoValorTotalPedidos-SpringBoot
```

2. **Execute o projeto usando Maven Wrapper**
```bash
# No Linux/Mac
./mvnw spring-boot:run

# No Windows
mvnw.cmd spring-boot:run
```

Ou, se tiver o Maven instalado:
```bash
mvn spring-boot:run
```

3. **Interaja com o sistema via console**

O sistema solicitará a entrada de dados via terminal:
```
Digite os dados do pedido:
<código do pedido>
<valor básico>
<percentual de desconto>
```

### Exemplo de uso

**Fluxo de entrada:**

```
=== SISTEMA DE CÁLCULO DE VALOR TOTAL DE PEDIDOS ===

Digite os dados do pedido:
Código do pedido: 1034
Valor básico do pedido (R$): 150.00
Percentual de desconto (%): 20.0
```

**Saída esperada:**

```
========================================
                 RESULTADO FINAL
========================================
Código do pedido:        1034
Valor básico:            R$ 150,00
Desconto (%):            20,0%
Valor do desconto:       R$ 30,00
Subtotal com desconto:   R$ 120,00
Frete:                   R$ 12,00
----------------------------------------
VALOR TOTAL:             R$ 132,00
========================================
```

## Executar Testes

```bash
# Usando Maven Wrapper
./mvnw test

# Ou com Maven instalado
mvn test
```

Mais detalhes em `TESTES.md`.

## Conceitos Aplicados

- **Injeção de Dependência**: Utilização do Spring Framework para gerenciar dependências entre componentes
- **Componentização**: Separação de responsabilidades em serviços especializados
- **Programação Orientada a Objetos**: Encapsulamento de lógica de negócio em classes e métodos
- **Clean Code**: Código organizado e de fácil manutenção

## Autor

**Guilherme Cursino** - [@Guilherme-Cursino](https://github.com/Guilherme-Cursino)
