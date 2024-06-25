package edu.icet.clothify.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class PaymentType {
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    private String type;

    private Set<Payment> paymentSet;
}
