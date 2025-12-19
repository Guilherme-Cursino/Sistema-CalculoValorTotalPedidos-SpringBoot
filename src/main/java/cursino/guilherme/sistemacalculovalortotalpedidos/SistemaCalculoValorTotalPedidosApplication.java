package cursino.guilherme.sistemacalculovalortotalpedidos;

import cursino.guilherme.sistemacalculovalortotalpedidos.entities.Order;
import cursino.guilherme.sistemacalculovalortotalpedidos.services.OrderService;
import cursino.guilherme.sistemacalculovalortotalpedidos.services.ShippingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@SpringBootApplication
public class SistemaCalculoValorTotalPedidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaCalculoValorTotalPedidosApplication.class, args);
    }

    // Disclaimer for international developers:
    // This application was originally designed to be used by
    // brazilian Portuguese-speaking users.
    // The following prompts and outputs are in Portuguese to ensure clarity and
    // usability for the target audience. As well as the brazilian currency (Real -
    // R$) formatting.
    // Feel free to modify the language and currency formatting as needed for your
    // use case.
    @Bean
    @Profile("!test")
    public CommandLineRunner commandLineRunner() {
        return args -> {
            try {
                Integer code;
                Double basic;
                Double discount;

                // Check if arguments are provided via command line
                if (args.length >= 3) {
                    try {
                        code = Integer.parseInt(args[0]);
                        basic = Double.parseDouble(args[1]);
                        discount = Double.parseDouble(args[2]);
                    } catch (NumberFormatException e) {
                        System.err.println("Erro: Argumentos inválidos. Use: <código> <valor_básico> <desconto>");
                        System.err.println("Exemplo: 1034 150.00 20.0");
                        return;
                    }
                } else {
                    // Interactive mode using BufferedReader for better input handling
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                    System.out.println("\n=== SISTEMA DE CÁLCULO DE VALOR TOTAL DE PEDIDOS ===\n");
                    System.out.println("Digite os dados do pedido:");

                    System.out.print("Código do pedido: ");
                    String codeLine = reader.readLine();
                    if (codeLine == null || codeLine.trim().isEmpty()) {
                        System.err.println("Erro: Nenhuma entrada fornecida para o código.");
                        return;
                    }

                    try {
                        code = Integer.parseInt(codeLine.trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Erro: Por favor, insira um número inteiro válido para o código.");
                        return;
                    }

                    System.out.print("Valor básico do pedido (R$): ");
                    String basicLine = reader.readLine();
                    if (basicLine == null || basicLine.trim().isEmpty()) {
                        System.err.println("Erro: Nenhuma entrada fornecida para o valor básico.");
                        return;
                    }

                    try {
                        basic = Double.parseDouble(basicLine.trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Erro: Por favor, insira um valor decimal válido.");
                        return;
                    }

                    System.out.print("Percentual de desconto (%): ");
                    String discountLine = reader.readLine();
                    if (discountLine == null || discountLine.trim().isEmpty()) {
                        System.err.println("Erro: Nenhuma entrada fornecida para o desconto.");
                        return;
                    }

                    try {
                        discount = Double.parseDouble(discountLine.trim());
                    } catch (NumberFormatException e) {
                        System.err.println("Erro: Por favor, insira um percentual válido.");
                        return;
                    }
                }

                // Create order and calculate total
                Order order = new Order(code, basic, discount);
                ShippingService shippingService = new ShippingService();
                OrderService orderService = new OrderService(shippingService);
                Double total = orderService.total(order);

                // Display results
                Double discountAmount = order.getBasic() * order.getDiscount() / 100;
                Double basicWithDiscount = order.getBasic() - discountAmount;
                Double shipping = shippingService.shipment(order);

                System.out.println("\n" + "=".repeat(40));
                System.out.println("                 RESULTADO FINAL");
                System.out.println("=".repeat(40));
                System.out.println("Código do pedido:        " + order.getCode());
                System.out.println("Valor básico:            R$ " + String.format("%.2f", order.getBasic()));
                System.out.println("Desconto (%):            " + String.format("%.1f%%", order.getDiscount()));
                System.out.println("Valor do desconto:       R$ " + String.format("%.2f", discountAmount));
                System.out.println("Subtotal com desconto:   R$ " + String.format("%.2f", basicWithDiscount));
                System.out.println("Frete:                   R$ " + String.format("%.2f", shipping));
                System.out.println("-".repeat(40));
                System.out.println("VALOR TOTAL:             R$ " + String.format("%.2f", total));
                System.out.println("=".repeat(40) + "\n");

            } catch (Exception e) {
                System.err.println("Erro ao processar o pedido: " + e.getMessage());
            }
        };
    }
}
