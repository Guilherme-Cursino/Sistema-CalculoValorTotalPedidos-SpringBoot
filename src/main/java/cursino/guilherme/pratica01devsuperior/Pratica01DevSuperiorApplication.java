package cursino.guilherme.pratica01devsuperior;

import cursino.guilherme.pratica01devsuperior.entities.Order;
import cursino.guilherme.pratica01devsuperior.services.OrderService;
import cursino.guilherme.pratica01devsuperior.services.ShippingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Pratica01DevSuperiorApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Pratica01DevSuperiorApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner input = new Scanner(System.in);

		System.out.println("Digite os dados do pedido:");
		Integer code = input.nextInt();
		Double basic = input.nextDouble();
		Double discount = input.nextDouble();
		Order order = new Order(code, basic, discount);

		ShippingService shippingService = new ShippingService();
		OrderService orderService = new OrderService(shippingService);

		Double total = orderService.total(order);

		System.out.println("Código do pedido: " + order.getCode() +
							System.lineSeparator() +
							"Valor total: " + total);

		input.close();
	}
}
