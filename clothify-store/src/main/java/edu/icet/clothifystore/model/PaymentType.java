package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentType {
    private Integer id;
    private String type;
    private Set<Payment> paymentSet;
}
