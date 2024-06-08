package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Integer productId;
    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Set<Item> itemSet;
}
