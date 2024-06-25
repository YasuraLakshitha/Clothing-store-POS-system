package edu.icet.clothify.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Employee {
    @EqualsAndHashCode.Include
    private String id;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;


    private Set<Order> orderSet;


    private Admin admin;


    private Set<OrderDetails> orderDetailsSet;
}
