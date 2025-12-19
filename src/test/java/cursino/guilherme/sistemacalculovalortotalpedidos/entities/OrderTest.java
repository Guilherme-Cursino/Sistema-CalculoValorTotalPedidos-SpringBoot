package cursino.guilherme.sistemacalculovalortotalpedidos.entities;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Order Entity Tests")
class OrderTest {

    @Test
    @DisplayName("Should create order with valid data")
    void shouldCreateOrderWithValidData() {
        // Arrange & Act
        Order order = new Order(1001, 150.00, 10.0);

        // Assert
        assertNotNull(order);
        assertEquals(1001, order.getCode());
        assertEquals(150.00, order.getBasic());
        assertEquals(10.0, order.getDiscount());
    }

    @Test
    @DisplayName("Should create order with zero discount")
    void shouldCreateOrderWithZeroDiscount() {
        // Arrange & Act
        Order order = new Order(1002, 200.00, 0.0);

        // Assert
        assertEquals(0.0, order.getDiscount());
        assertEquals(200.00, order.getBasic());
    }

    @Test
    @DisplayName("Should create order with maximum discount")
    void shouldCreateOrderWithMaximumDiscount() {
        // Arrange & Act
        Order order = new Order(1003, 100.00, 100.0);

        // Assert
        assertEquals(100.0, order.getDiscount());
    }

    @Test
    @DisplayName("Should maintain order data immutability")
    void shouldMaintainOrderDataImmutability() {
        // Arrange
        Integer expectedCode = 1004;
        Double expectedBasic = 250.00;
        Double expectedDiscount = 15.0;

        // Act
        Order order = new Order(expectedCode, expectedBasic, expectedDiscount);

        // Assert
        assertEquals(expectedCode, order.getCode());
        assertEquals(expectedBasic, order.getBasic());
        assertEquals(expectedDiscount, order.getDiscount());
    }

    @Test
    @DisplayName("Should handle small order values")
    void shouldHandleSmallOrderValues() {
        // Arrange & Act
        Order order = new Order(1005, 50.00, 5.0);

        // Assert
        assertEquals(50.00, order.getBasic());
        assertTrue(order.getBasic() < 100);
    }

    @Test
    @DisplayName("Should handle large order values")
    void shouldHandleLargeOrderValues() {
        // Arrange & Act
        Order order = new Order(1006, 1000.00, 20.0);

        // Assert
        assertEquals(1000.00, order.getBasic());
        assertTrue(order.getBasic() >= 200);
    }
}
