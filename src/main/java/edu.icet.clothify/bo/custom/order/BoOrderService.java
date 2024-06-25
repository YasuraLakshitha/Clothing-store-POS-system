package edu.icet.clothify.bo.custom.order;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Order;

import java.util.List;

public interface BoOrderService extends Service<Order> {
    String generateOrderId();

    List<Order> findAll();

    Order findByCustomerId(String id);
}
