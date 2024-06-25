package edu.icet.clothify.dao.custom.order_details;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;

import java.util.List;

public interface OrderDetailsRepository extends Crud<OrderDetailsEntity, String> {
    List<OrderDetailsEntity> findAll();

    List<OrderDetailsEntity> findByOrder(OrderEntity order);
}
