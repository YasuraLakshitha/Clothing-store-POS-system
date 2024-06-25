package edu.icet.clothify.dao.custom.employee;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends Crud<EmployeeEntity, String> {
    List<EmployeeEntity> findAll();

    EmployeeEntity findByEmailAndPassword(String email, String password);

    EmployeeEntity findByEmail(String email);
}
