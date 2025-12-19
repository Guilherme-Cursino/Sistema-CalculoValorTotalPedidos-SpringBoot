package cursino.guilherme.sistemacalculovalortotalpedidos.services;

import cursino.guilherme.sistemacalculovalortotalpedidos.entities.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("OrderService Tests")
class OrderServiceTest {

    @Mock
    private ShippingService shippingService;

    @InjectMocks
    private OrderService orderService;

    @Test
    @DisplayName("Should calculate total with discount and shipping - Case 1")
    void shouldCalculateTotalCase1() {
        // Arrange
        // Code 1034, basic value R$150.00, discount 20%, expected total R$132.00
        Order order = new Order(1034, 150.00, 20.0);
        when(shippingService.shipment(order)).thenReturn(12.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        assertEquals(132.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total with discount and shipping - Case 2")
    void shouldCalculateTotalCase2() {
        // Arrange
        // Code 2362, basic value R$800.00, discount 10%, expected total R$720.00
        Order order = new Order(2362, 800.00, 10.0);
        when(shippingService.shipment(order)).thenReturn(0.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        assertEquals(720.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total with discount and shipping - Case 3")
    void shouldCalculateTotalCase3() {
        // Arrange
        // Code 4657, basic value R$95.90, discount 5%, expected total R$111.12
        Order order = new Order(4657, 95.90, 5.0);
        when(shippingService.shipment(order)).thenReturn(20.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        assertEquals(111.11, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total with zero discount")
    void shouldCalculateTotalWithZeroDiscount() {
        // Arrange
        Order order = new Order(1001, 100.00, 0.0);
        when(shippingService.shipment(order)).thenReturn(12.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        assertEquals(112.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total with 100% discount")
    void shouldCalculateTotalWith100PercentDiscount() {
        // Arrange
        Order order = new Order(1002, 100.00, 100.0);
        when(shippingService.shipment(order)).thenReturn(12.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        // 100% discount means only shipping cost
        assertEquals(12.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total with free shipping")
    void shouldCalculateTotalWithFreeShipping() {
        // Arrange
        Order order = new Order(1003, 250.00, 10.0);
        when(shippingService.shipment(order)).thenReturn(0.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        assertEquals(225.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total for small order with high shipping")
    void shouldCalculateTotalForSmallOrder() {
        // Arrange
        Order order = new Order(1004, 50.00, 10.0);
        when(shippingService.shipment(order)).thenReturn(20.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        // 50 - (50 * 0.10) + 20 = 50 - 5 + 20 = 65
        assertEquals(65.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total for medium order")
    void shouldCalculateTotalForMediumOrder() {
        // Arrange
        Order order = new Order(1005, 150.00, 15.0);
        when(shippingService.shipment(order)).thenReturn(12.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        // 150 - (150 * 0.15) + 12 = 150 - 22.5 + 12 = 139.5
        assertEquals(139.50, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should calculate total for large order")
    void shouldCalculateTotalForLargeOrder() {
        // Arrange
        Order order = new Order(1006, 1000.00, 5.0);
        when(shippingService.shipment(order)).thenReturn(0.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        // 1000 - (1000 * 0.05) + 0 = 1000 - 50 = 950
        assertEquals(950.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should apply discount before adding shipping")
    void shouldApplyDiscountBeforeAddingShipping() {
        // Arrange
        Order order = new Order(1007, 100.00, 50.0);
        when(shippingService.shipment(order)).thenReturn(12.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        // 100 - (100 * 0.50) + 12 = 100 - 50 + 12 = 62
        assertEquals(62.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }

    @Test
    @DisplayName("Should handle fractional discount percentages")
    void shouldHandleFractionalDiscounts() {
        // Arrange
        Order order = new Order(1008, 200.00, 12.5);
        when(shippingService.shipment(order)).thenReturn(0.00);

        // Act
        Double total = orderService.total(order);

        // Assert
        // 200 - (200 * 0.125) + 0 = 200 - 25 = 175
        assertEquals(175.00, total, 0.01);
        verify(shippingService, times(1)).shipment(order);
    }
}
