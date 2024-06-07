package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private String id;
    private String productName;
    private Integer productSize;
    private Double productPrice;
    private Integer productQuantity;

    private Admin admin;

    private Category category;

    private Order order;

    private Set<SupplierProduct> supplierProductSet;

    private Set<OrderDetails> orderDetailsSet;
}
