package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    private String id;
    private String adminName;
    private String adminPassword;
    private String adminEmail;
    private Set<Employee> employeeSet;
    private Set<Product> productSet;
}
