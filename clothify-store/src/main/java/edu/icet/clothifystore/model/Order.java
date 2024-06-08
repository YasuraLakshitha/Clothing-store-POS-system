package edu.icet.clothifystore.model;

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
    private Set<Item> itemSet;
    private Set<OrderDetails> orderDetailsSet;
}
