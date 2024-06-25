package edu.icet.clothify.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Customer {
    @EqualsAndHashCode.Include
    private String id;
    private String customerName;
    private String customerEmail;
    private String customerContact;
    private Set<Order> orderSet;
}
