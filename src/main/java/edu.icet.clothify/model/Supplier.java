package edu.icet.clothify.model;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Supplier {

    @EqualsAndHashCode.Include
    private String id;

    private String staffId;

    private String supplierName;

    private String supplierEmail;

    private String supplierPassword;

    private String companyName;

    private Map<Integer, ArrayList<Item>> supplierItemRecord;

    @JsonIdentityReference(alwaysAsId = true)
    private Set<Item> ItemSet;
}
