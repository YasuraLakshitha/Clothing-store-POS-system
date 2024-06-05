package edu.icet.clothifystore.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee{
    private String id;
    private String employeeName;
    private String employeeEmail;
    private String employeePassword;
    private Set<Order> orderSet;
    private Admin admin;
    private Set<OrderDetails> orderDetailsSet;
}
