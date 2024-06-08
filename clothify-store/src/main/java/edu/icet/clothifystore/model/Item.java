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
    private String productName;
    private Integer productSize;
    private Double productPrice;
    private Integer productQuantity;

    private Product product;

    private Order order;

    private Set<SupplierItem> supplierItemSet;

    private Set<OrderDetails> orderDetailsSet;
}
