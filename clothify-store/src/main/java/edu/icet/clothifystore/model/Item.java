package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    private String id;
    private String itemName;
    private Integer itemSize;
    private Double itemPrice;
    private Integer itemQuantity;

    private Product product;

    private Order order;

    private Set<SupplierItem> supplierItemSet;

    private Set<OrderDetails> orderDetailsSet;
}
