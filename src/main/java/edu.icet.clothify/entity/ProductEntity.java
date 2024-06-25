package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class ProductEntity {
    @Id
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;
    private String productName;
    private String productDescription;
    private Integer productQuantity;
    private Double productPrice;

    @OneToMany(mappedBy = "productEntity", fetch = FetchType.EAGER)
    private Set<ItemEntity> itemEntitySet;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

}
