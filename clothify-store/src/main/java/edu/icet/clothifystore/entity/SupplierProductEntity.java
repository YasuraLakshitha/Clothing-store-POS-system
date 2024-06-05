package edu.icet.clothifystore.entity;

import edu.icet.clothifystore.util.config.SupplierProductId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "supplier_product")
public class SupplierProductEntity {
    @EmbeddedId
    private SupplierProductId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private ProductEntity productEntity;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;
}
