package edu.icet.clothify.persistence.tabel_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblProductQuantity {
    private String productName;
    private Integer quantity;
}
