package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class EmployeeEntity {
    @Id
    @Column(name = "employee_id")
    @ToString.Include
    @EqualsAndHashCode.Include
    private String id;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderEntity> orderEntitySet;

    @ManyToOne()
    @JoinColumn(name = "admin_id")
    private AdminEntity adminEntity;

    @OneToMany(mappedBy = "employeeEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<OrderDetailsEntity> orderDetailsEntitySet;
}
