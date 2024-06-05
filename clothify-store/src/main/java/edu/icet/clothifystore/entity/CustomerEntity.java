package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private Integer id;
    private String customerName;
    private String customerEmail;
    private String customerContact;

    @OneToMany(mappedBy = "customerEntity")
    private Set<OrderEntity> orderEntitySet;
}
