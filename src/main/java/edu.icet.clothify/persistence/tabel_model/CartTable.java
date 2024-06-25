package edu.icet.clothify.persistence.tabel_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartTable {
    private String itemId;
    private String itemDescription;
    private String itemSize;
    private Integer itemQuantity;
    private Double itemPrice;
}
