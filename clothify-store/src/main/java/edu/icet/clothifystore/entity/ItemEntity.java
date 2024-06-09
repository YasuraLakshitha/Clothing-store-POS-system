package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "item")
public class ItemEntity {
    @Id
    @Column(name = "item_id")
    private String id;
    private String itemName;
    private Integer itemSize;
    private Double itemPrice;
    private Integer itemQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @OneToMany(mappedBy = "itemEntity")
    private Set<SupplierItemEntity> supplieritemEntitySet;

    @OneToMany(mappedBy = "itemEntity")
    private Set<OrderDetailsEntity> orderDetailsEntitySet;
}
