package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import edu.icet.clothify.model.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "supplier")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class SupplierEntity {
    @Id
    @Column(name = "supplier_id")
    @EqualsAndHashCode.Include
    private String id;
    private String staffId;
    private String supplierName;
    private String supplierEmail;
    private String supplierPassword;
    private String companyName;

    @Transient
    private Map<Integer, ArrayList<Item>> supplierItemRecord;

    @OneToMany(mappedBy = "supplierEntity", cascade = CascadeType.ALL)
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("itemSet")
    private Set<ItemEntity> itemEntitySet;
}
