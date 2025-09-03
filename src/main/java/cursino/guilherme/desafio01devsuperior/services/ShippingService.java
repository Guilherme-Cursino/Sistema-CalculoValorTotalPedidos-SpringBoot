package cursino.guilherme.desafio01devsuperior.services;

import cursino.guilherme.desafio01devsuperior.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double shipment(Order order){
        double shipment;
        if(order.getBasic() < 100){
            shipment = 20.00;
        } else if(order.getBasic() > 100 && order.getBasic() < 200){
            shipment = 12.00;
        } else {
            shipment = 0.00;
        }
        return shipment;
    }
}
