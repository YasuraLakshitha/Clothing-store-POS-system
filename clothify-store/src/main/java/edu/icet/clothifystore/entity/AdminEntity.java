package edu.icet.clothifystore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "admin")
public class AdminEntity {
    @Id
    @Column(name = "admin_id")
    private String id;
    private String adminName;
    private String adminPassword;
    private String adminEmail;

    @OneToMany(mappedBy = "adminEntity")
    private Set<EmployeeEntity> employeeEntitySet;

}
