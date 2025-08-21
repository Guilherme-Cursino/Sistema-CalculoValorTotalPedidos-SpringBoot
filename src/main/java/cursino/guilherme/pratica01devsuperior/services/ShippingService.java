package cursino.guilherme.pratica01devsuperior.services;

import cursino.guilherme.pratica01devsuperior.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double shipment(Order order){
        double shipment;
        if(order.getBasic() < 100){
            shipment = 20D;
        } else if(order.getBasic() > 100 && order.getBasic() < 200){
            shipment = 12D;
        } else {
            shipment = 0D;
        }
        return shipment;
    }
}
