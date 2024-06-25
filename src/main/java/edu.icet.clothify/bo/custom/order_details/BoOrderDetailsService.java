package edu.icet.clothify.bo.custom.order_details;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Order;
import edu.icet.clothify.model.OrderDetails;

import java.util.List;

public interface BoOrderDetailsService extends Service<OrderDetails> {
    List<OrderDetails> findByOrder(Order order);
}
