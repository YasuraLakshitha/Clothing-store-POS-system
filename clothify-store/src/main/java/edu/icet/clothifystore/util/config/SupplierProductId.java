package edu.icet.clothifystore.util.config;

import edu.icet.clothifystore.entity.ProductEntity;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class SupplierProductId implements Serializable {
    private String supplierId;
    private String productId;
}
