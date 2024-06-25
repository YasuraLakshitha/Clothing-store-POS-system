package edu.icet.clothify.model;

import lombok.*;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Admin {
    @EqualsAndHashCode.Include
    @ToString.Include
    private String id;
    private String adminName;
    private String adminPassword;
    private String adminEmail;
    private Set<Employee> employeeSet;
}
