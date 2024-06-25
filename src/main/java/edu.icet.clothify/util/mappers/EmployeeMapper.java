package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.model.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmployeeMapper {


    ModelMapper modelMapper = new ModelMapper();

    public EmployeeMapper() {
        employeeMapperConfig();
    }

    private void employeeMapperConfig() {
        TypeMap<Employee, EmployeeEntity> employeeEmployeeEntityTypeMap = modelMapper.createTypeMap(Employee.class, EmployeeEntity.class);
        employeeEmployeeEntityTypeMap.addMappings(mapping -> {
            mapping.map(Employee::getId, EmployeeEntity::setId);
            mapping.map(Employee::getEmployeeName, EmployeeEntity::setEmployeeName);
            mapping.map(Employee::getEmployeeEmail, EmployeeEntity::setEmployeeEmail);
            mapping.map(Employee::getEmployeePassword, EmployeeEntity::setEmployeePassword);
            try {
                mapping.map(source -> source != null ? modelMapper.map(source.getAdmin(), AdminEntity.class) : null, EmployeeEntity::setAdminEntity);
            } catch (Exception e) {
                System.out.println("Employee Mapper could not be converted to EmployeeEntity.Admin");
            }

        });
        TypeMap<EmployeeEntity, Employee> employeeEntityEmployeeTypeMap = modelMapper.createTypeMap(EmployeeEntity.class, Employee.class);
        employeeEntityEmployeeTypeMap.addMappings(mapping -> {
            mapping.map(EmployeeEntity::getId, Employee::setId);
            mapping.map(EmployeeEntity::getEmployeeName, Employee::setEmployeeName);
            mapping.map(EmployeeEntity::getEmployeeEmail, Employee::setEmployeeEmail);
            mapping.map(EmployeeEntity::getEmployeePassword, Employee::setEmployeePassword);
        });
    }

    public Employee convert(final EmployeeEntity employeeEntity) {
        if (employeeEntity == null) {
            return null;
        }
        return modelMapper.map(employeeEntity, Employee.class);
    }

    public EmployeeEntity convert(final Employee employeeEntity) {
        return modelMapper.map(employeeEntity, EmployeeEntity.class);
    }
}
