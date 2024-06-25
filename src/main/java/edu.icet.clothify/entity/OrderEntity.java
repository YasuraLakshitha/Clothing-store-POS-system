package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer_order")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;
    private String state;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false)
    private CustomerEntity customerEntity;

    @OneToOne(mappedBy = "orderEntity")
    private PaymentEntity paymentEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "employee_id", nullable = false)
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER)
    private Set<ItemEntity> itemEntitySet;

    @OneToMany(mappedBy = "orderEntity", fetch = FetchType.EAGER)
    private Set<OrderDetailsEntity> orderDetailsEntitySet;
}
