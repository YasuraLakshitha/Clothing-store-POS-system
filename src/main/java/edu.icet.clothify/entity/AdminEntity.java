package edu.icet.clothify.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "admin")
@ToString(onlyExplicitlyIncluded = true)
public class AdminEntity {
    @Id
    @Column(name = "admin_id")
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;
    private String adminName;
    private String adminPassword;
    private String adminEmail;

    @OneToMany(mappedBy = "adminEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EmployeeEntity> employeeEntitySet;

}
