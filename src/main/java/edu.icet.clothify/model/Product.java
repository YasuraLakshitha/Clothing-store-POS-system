package edu.icet.clothify.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
public class Product {
    @ToString.Include
    private String id;

    private String productName;

    private String productDescription;

    private Integer productQuantity;

    private Double productPrice;

    private Set<Item> itemSet;

    private Category category;

}
