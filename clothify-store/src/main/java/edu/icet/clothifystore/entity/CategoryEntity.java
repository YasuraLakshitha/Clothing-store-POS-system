package edu.icet.clothifystore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "category")
public class CategoryEntity {
    @Id
    @Column(name = "category_id")
    private String id;
    private String categoryType;
    private String categoryDescription;

    @OneToMany(mappedBy = "categoryEntity")
    private Set<ProductEntity> productsEntitiySet;
}
