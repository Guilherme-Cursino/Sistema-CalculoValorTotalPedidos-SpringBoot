package cursino.guilherme.sistemacalculovalortotalpedidos;

import cursino.guilherme.sistemacalculovalortotalpedidos.services.OrderService;
import cursino.guilherme.sistemacalculovalortotalpedidos.services.ShippingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")
@DisplayName("Application Context Tests")
class SistemaCalculoValorTotalPedidosApplicationTests {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ShippingService shippingService;

    @Test
    @DisplayName("Should load application context successfully")
    void contextLoads() {
    }

    @Test
    @DisplayName("Should autowire OrderService bean")
    void shouldAutowireOrderService() {
        assertNotNull(orderService);
    }

    @Test
    @DisplayName("Should autowire ShippingService bean")
    void shouldAutowireShippingService() {
        assertNotNull(shippingService);
    }
}
