package edu.icet.clothifystore.bo.custom.employee.impl;

import edu.icet.clothifystore.bo.custom.employee.BoEmployeeService;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.employee.EmployeeRepository;
import edu.icet.clothifystore.entity.AdminEntity;
import edu.icet.clothifystore.entity.EmployeeEntity;
import edu.icet.clothifystore.model.Employee;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoEmployeeServiceImpl implements BoEmployeeService {
    private final EmployeeRepository employeeRepository = new DaoFactory().getDaoImpl(DaoType.EMPLOYEE);
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean save(Employee object) {
        AdminEntity adminEntity = modelMapper.map(object.getAdmin(), AdminEntity.class);
        EmployeeEntity employeeEntity = modelMapper.map(object, EmployeeEntity.class);
        employeeEntity.setAdminEntity(adminEntity);
        return employeeRepository.save(employeeEntity);
    }

    @Override
    public Boolean update(String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Employee findById(String id) {
        return null;
    }

    @Override
    public String generateEmployeeId() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        if (employeeEntities == null || employeeEntities.isEmpty()) return "EM001";
        Pattern patter = Pattern.compile("EM([0-9]{3})");
        Matcher matcher = patter.matcher(
                employeeEntities.get(employeeEntities.size()-1).getId());
        Integer nextId = null;
        while (matcher.find()) {
             nextId = Integer.parseInt(matcher.group(1));
        }
        if (nextId != null) return String.format("EM%03d",++nextId);
        return null;
    }

    @Override
    public List<Employee> findAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();
        for (EmployeeEntity employeeEntity : employeeEntities) {
            employees.add(modelMapper.map(employeeEntity, Employee.class));
        }
        return employees;
     }

     @Override
    public  List<String> loadEmployeeIds(){
        List<Employee> employees = findAllEmployees();
        List<String> employeeIds = new ArrayList<>();
        for (Employee employee : employees) {
            employeeIds.add(employee.getId());
        }
        return employeeIds;
     }
}
