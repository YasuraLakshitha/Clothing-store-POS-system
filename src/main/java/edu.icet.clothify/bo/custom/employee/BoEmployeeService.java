package edu.icet.clothify.bo.custom.employee;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Employee;

import java.util.List;

public interface BoEmployeeService extends Service<Employee> {
    String generateEmployeeId();

    List<Employee> findAllEmployees();

    List<String> loadEmployeeIds();

    Employee findByEmployeeByEmailAndPassword(String email, String password);

    Employee findEmployeeByEmail(String email);
}
