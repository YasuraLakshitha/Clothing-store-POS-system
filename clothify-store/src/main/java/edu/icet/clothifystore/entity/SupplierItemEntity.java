package edu.icet.clothifystore.entity;

import edu.icet.clothifystore.util.config.SupplierItemId;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "supplier_item")
public class SupplierItemEntity {
    @EmbeddedId
    private SupplierItemId id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;

    @ManyToOne
    @MapsId("supplierId")
    @JoinColumn(name = "supplier_id")
    private SupplierEntity supplierEntity;
}
