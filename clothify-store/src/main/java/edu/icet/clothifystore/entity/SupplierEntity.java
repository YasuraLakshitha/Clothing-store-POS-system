package edu.icet.clothifystore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "supplier")
public class SupplierEntity {
    @Id
    private String id;
    private String staffId;
    private String supplierName;
    private String supplierEmail;
    private String supplierPassword;
    private String companyName;
    private String item;

    @OneToMany(mappedBy = "supplierEntity")
    private Set<SupplierItemEntity> supplierItemEntitySet;
}
