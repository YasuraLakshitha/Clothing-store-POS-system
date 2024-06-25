package edu.icet.clothify.dao.custom.order;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.OrderEntity;

import java.util.List;

public interface OrderRepository extends Crud<OrderEntity, String> {
    List<OrderEntity> findAll();

    OrderEntity findByCustomer(String id);
}
