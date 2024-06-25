package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "payment_type")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class PaymentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_type_id")
    @EqualsAndHashCode.Include
    private Integer id;

    private String type;

    @OneToMany(mappedBy = "paymentTypeEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PaymentEntity> paymentEntitySet;
}
