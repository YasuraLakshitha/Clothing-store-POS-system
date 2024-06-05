package edu.icet.clothifystore.model;

import edu.icet.clothifystore.entity.EmployeeEntity;
import edu.icet.clothifystore.entity.OrderEntity;
import edu.icet.clothifystore.entity.ProductEntity;
import edu.icet.clothifystore.util.config.OrderDetailsId;

public class OrderDetails {
    private OrderDetailsId id;
    private Employee employee;
    private Order order;
    private Product product;
}
