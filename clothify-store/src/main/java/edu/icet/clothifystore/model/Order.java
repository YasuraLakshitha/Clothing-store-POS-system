package edu.icet.clothifystore.model;

import edu.icet.clothifystore.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String id;
    private String state;
    private Customer customer;
    private Payment payment;
    private Employee employee;
    private Set<Product> productSet;
    private Set<OrderDetails> orderDetailsSet;
}
