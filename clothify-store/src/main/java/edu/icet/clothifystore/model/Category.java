package edu.icet.clothifystore.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private String id;
    private String categoryType;
    private String categoryDescription;
    private Set<Product> productSet;
}
