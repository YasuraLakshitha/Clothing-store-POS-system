package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Integer id;

    private Double netAmount;

    @ManyToOne
    @JoinColumn(name = "payment_type_id")
    private PaymentTypeEntity paymentType;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;
}
