package edu.icet.clothify.bo.custom.employee.impl;

import edu.icet.clothify.bo.custom.employee.BoEmployeeService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.employee.EmployeeRepository;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.model.Employee;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.EmployeeMapper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BoEmployeeServiceImpl implements BoEmployeeService {

    private final EmployeeRepository employeeRepository = new DaoFactory().getDaoImpl(DaoType.EMPLOYEE);

    private final EmployeeMapper employeeMapper = new EmployeeMapper();

    @Override
    public Boolean save(Employee object) {
        return employeeRepository.save(employeeMapper.convert(object));
    }

    @Override
    public Boolean update(Employee object) {
        return employeeRepository.update(employeeMapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return employeeRepository.delete(id);
    }

    @Override
    public Employee findById(String id) {
        return employeeMapper.convert(employeeRepository.findById(id));
    }

    @Override
    public String generateEmployeeId() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        if (employeeEntities == null) return "EMP001";
        Pattern patter = Pattern.compile("(EMP)([0-9]{3})");
        Matcher matcher = patter.matcher(employeeEntities.get(employeeEntities.size() - 1).getId());
        if (matcher.find())
            return String.format("EMP%03d", Integer.parseInt(matcher.group(2)) + 1);
        return null;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(employeeMapper::convert).collect(Collectors.toList());
    }

    @Override
    public List<String> loadEmployeeIds() {
        return employeeRepository.findAll().stream()
                .map(EmployeeEntity::getId).collect(Collectors.toList());
    }

    @Override
    public Employee findByEmployeeByEmailAndPassword(String email, String password) {
        return employeeMapper.convert(employeeRepository.findByEmailAndPassword(email, password));
    }

    @Override
    public Employee findEmployeeByEmail(String email) {
        return employeeMapper.convert(employeeRepository.findByEmail(email));
    }
}
