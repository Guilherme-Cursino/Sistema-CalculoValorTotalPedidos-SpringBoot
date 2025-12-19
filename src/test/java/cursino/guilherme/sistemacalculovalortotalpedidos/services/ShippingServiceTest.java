package cursino.guilherme.sistemacalculovalortotalpedidos.services;

import cursino.guilherme.sistemacalculovalortotalpedidos.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ShippingService Tests")
class ShippingServiceTest {

    private ShippingService shippingService;

    @BeforeEach
    void setUp() {
        shippingService = new ShippingService();
    }

    @Test
    @DisplayName("Should charge R$20.00 for orders below R$100.00")
    void shouldCharge20ForOrdersBelow100() {
        // Arrange
        Order order = new Order(1001, 50.00, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(20.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$20.00 for order at R$99.99")
    void shouldCharge20ForOrderAt99_99() {
        // Arrange
        Order order = new Order(1002, 99.99, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(20.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$12.00 for order at R$100.00")
    void shouldCharge12ForOrderAt100_00() {
        // Arrange
        Order order = new Order(1003, 100.00, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(12.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$12.00 for orders between R$100.01 and R$200.00")
    void shouldCharge12ForOrdersBetween100And200() {
        // Arrange
        Order order = new Order(1004, 150.00, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(12.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$12.00 for order at R$199.99")
    void shouldCharge12ForOrderAt199_99() {
        // Arrange
        Order order = new Order(1005, 199.99, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(12.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$0.00 (free shipping) for orders R$200.00 or more")
    void shouldChargeFreeForOrders200OrMore() {
        // Arrange
        Order order = new Order(1006, 200.00, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(0.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$0.00 for order at R$200.01")
    void shouldChargeFreeForOrderAt200_01() {
        // Arrange
        Order order = new Order(1007, 200.01, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(0.00, shipmentCost);
    }

    @Test
    @DisplayName("Should charge R$0.00 for large orders")
    void shouldChargeFreeForLargeOrders() {
        // Arrange
        Order order = new Order(1008, 1000.00, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(0.00, shipmentCost);
    }

    @Test
    @DisplayName("Should handle minimum order value")
    void shouldHandleMinimumOrderValue() {
        // Arrange
        Order order = new Order(1009, 0.01, 0.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        assertEquals(20.00, shipmentCost);
    }

    @Test
    @DisplayName("Should calculate shipping based on basic value ignoring discount")
    void shouldCalculateShippingBasedOnBasicValue() {
        // Arrange - High discount but basic value determines shipping
        Order order = new Order(1010, 150.00, 90.0);

        // Act
        Double shipmentCost = shippingService.shipment(order);

        // Assert
        // Should be R$12.00 because basic value is R$150.00, not the discounted value
        assertEquals(12.00, shipmentCost);
    }
}
