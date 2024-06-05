package edu.icet.clothifystore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "employee")
public class EmployeeEntity{
    @Id
    @Column(name = "employee_id")
    private String id;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;

    @OneToMany(mappedBy = "employeeEntity")
    private Set<OrderEntity> orderEntitySet;

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private AdminEntity adminEntity;

    @OneToMany(mappedBy = "employeeEntity")
    private Set<OrderDetailsEntity> orderDetailsEntitySet;
}
