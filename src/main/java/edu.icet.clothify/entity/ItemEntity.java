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
@Entity(name = "item")
public class ItemEntity {
    @Id
    @Column(name = "item_id")
    private String id;
    private String itemName;
    private String itemSize;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;

    @OneToMany(mappedBy = "itemEntity", fetch = FetchType.EAGER)
    private Set<OrderDetailsEntity> orderDetailsEntitySet;
}
