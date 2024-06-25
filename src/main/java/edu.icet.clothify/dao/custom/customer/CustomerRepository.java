package edu.icet.clothify.dao.custom.customer;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends Crud<CustomerEntity, String> {
    List<CustomerEntity> getAll();
}
