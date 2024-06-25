package edu.icet.clothify.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public class Payment {
    @EqualsAndHashCode.Include
    private Integer id;

    private Double netAmount;

    private PaymentType paymentType;

    private Order order;
}
