package edu.icet.clothifystore.bo.custom.employee;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Employee;

import java.util.List;

public interface BoEmployeeService extends Service<Employee> {
    String generateEmployeeId();
    List<Employee> findAllEmployees();
    List<String> loadEmployeeIds();
}
