package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
    @Id
    private String productId;
    private String productName;
    private String productDescription;
    private Integer productQuantity;

    @OneToMany(mappedBy = "productEntity")
    private Set<ItemEntity> itemEntitySet;

    @ManyToOne()
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

}
