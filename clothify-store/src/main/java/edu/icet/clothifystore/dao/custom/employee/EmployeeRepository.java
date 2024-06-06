package edu.icet.clothifystore.dao.custom.employee;

import edu.icet.clothifystore.dao.Crud;
import edu.icet.clothifystore.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends Crud<EmployeeEntity,String> {
    List<EmployeeEntity> findAll();

    EmployeeEntity findByEmailAndPassword(String email, String password);

    EmployeeEntity findByEmail(String email);
}
