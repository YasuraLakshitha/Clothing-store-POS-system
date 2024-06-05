package edu.icet.clothifystore.model;

import edu.icet.clothifystore.entity.OrderEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private Integer  id;
    private String customerName;
    private String customerEmail;
    private String customerContact;
    private Set<Order> orderSet;
}
