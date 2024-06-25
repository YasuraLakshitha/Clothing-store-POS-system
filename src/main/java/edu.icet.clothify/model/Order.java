package edu.icet.clothify.model;

import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Order {
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;

    private Customer customer;

    private Payment payment;

    private Employee employee;

    private Set<Item> itemSet;

    private Set<OrderDetails> orderDetailsSet;
}
