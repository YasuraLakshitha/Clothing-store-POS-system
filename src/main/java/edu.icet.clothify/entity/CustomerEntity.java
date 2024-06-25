package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "customer")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CustomerEntity {
    @Id
    @Column(name = "customer_id")
    @EqualsAndHashCode.Include
    private String id;
    private String customerName;
    private String customerEmail;
    private String customerContact;

    @OneToMany(mappedBy = "customerEntity", fetch = FetchType.EAGER)
    private Set<OrderEntity> orderEntitySet;
}
