package edu.icet.clothify.model;

import lombok.*;

import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @EqualsAndHashCode.Include
    private String id;
    private String itemName;
    private String itemSize;

    private Product product;

    private Order order;

    private Supplier supplier;

    private Set<OrderDetails> orderDetailsSet;

}
