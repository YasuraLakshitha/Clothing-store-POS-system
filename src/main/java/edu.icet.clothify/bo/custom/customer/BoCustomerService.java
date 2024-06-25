package edu.icet.clothify.bo.custom.customer;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Customer;

import java.util.List;

public interface BoCustomerService extends Service<Customer> {
    String generateCustomerId();

    List<Customer> findAll();
}
