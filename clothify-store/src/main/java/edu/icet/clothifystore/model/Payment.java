package edu.icet.clothifystore.model;

import edu.icet.clothifystore.entity.OrderEntity;
import edu.icet.clothifystore.entity.PaymentTypeEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private Integer id;
    private Double neaAmount;
    private PaymentType paymentType;
    private Order order;
}
