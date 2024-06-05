package edu.icet.clothifystore.entity;

import edu.icet.clothifystore.model.Payment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment_type")
public class PaymentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id")
    private Integer id;

    private String type;

    @OneToMany(mappedBy = "paymentType")
    private Set<PaymentEntity> paymentEntitySet;
}
