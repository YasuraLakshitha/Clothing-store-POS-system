package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class ProductEntity {
    @Id
    @Column(name = "product_id")
    private String id;
    private String productName;
    private Integer productSize;
    private Double productPrice;
    private Integer productQuantity;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity adminEntity;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity categoryEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @OneToMany(mappedBy = "productEntity")
    private Set<SupplierProductEntity> supplierProductEntitySet;

    @OneToMany(mappedBy = "productEntity")
    private Set<OrderDetailsEntity> orderDetailsEntities;

}
