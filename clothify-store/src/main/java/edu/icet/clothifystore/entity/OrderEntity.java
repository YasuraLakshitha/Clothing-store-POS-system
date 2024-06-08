package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer_order")
public class OrderEntity {
    @Id
    @Column(name = "order_id")
    private String id;
    private String state;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private CustomerEntity customerEntity;

    @OneToOne(mappedBy = "orderEntity")
    private PaymentEntity paymentEntity;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;

    @OneToMany(mappedBy = "orderEntity")
    private Set<ItemEntity> itemEntitySet;

    @OneToMany(mappedBy = "orderEntity")
    private Set<OrderDetailsEntity> orderDetailsEntitySet;
}
