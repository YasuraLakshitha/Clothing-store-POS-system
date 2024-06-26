package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    private String id;
    private String supplierName;
    private String supplierEmail;
    private String supplierPassword;
    private String companyName;
    private String item;
    private Set<SupplierItem> supplierItemSet;
}
