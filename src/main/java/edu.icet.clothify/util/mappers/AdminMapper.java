package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.model.Admin;
import edu.icet.clothify.model.Employee;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.HashSet;
import java.util.stream.Collectors;

public class AdminMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public AdminMapper() {
        adminMapperConfig();
    }

    private void adminMapperConfig() {
        TypeMap<Admin, AdminEntity> adminAdminEntityTypeMap = modelMapper.createTypeMap(Admin.class, AdminEntity.class);
        adminAdminEntityTypeMap.addMappings(mapper -> {
            mapper.map(Admin::getId, AdminEntity::setId);
            mapper.map(Admin::getAdminName, AdminEntity::setAdminName);
            mapper.map(Admin::getAdminEmail, AdminEntity::setAdminEmail);
            mapper.map(Admin::getAdminPassword, AdminEntity::setAdminPassword);
            mapper.map(source -> source.getEmployeeSet() != null ?
                            source.getEmployeeSet().stream().map(employee -> modelMapper.map(employee, EmployeeEntity.class))
                                    .collect(Collectors.toSet()) : new HashSet<EmployeeEntity>()
                    , AdminEntity::setEmployeeEntitySet);
        });

        TypeMap<AdminEntity, Admin> adminEntityAdminTypeMap = modelMapper.createTypeMap(AdminEntity.class, Admin.class);
        adminEntityAdminTypeMap.addMappings(mapper -> {
            mapper.map(AdminEntity::getId, Admin::setId);
            mapper.map(AdminEntity::getAdminName, Admin::setAdminName);
            mapper.map(AdminEntity::getAdminEmail, Admin::setAdminEmail);
            mapper.map(AdminEntity::getAdminPassword, Admin::setAdminPassword);
            mapper.map(source -> source.getEmployeeEntitySet() != null ?
                            source.getEmployeeEntitySet().stream().map(employeeEntity -> modelMapper.map(employeeEntity, Employee.class))
                                    .collect(Collectors.toSet()) : new HashSet<Employee>()
                    , Admin::setEmployeeSet);
        });
    }

    public Admin convert(AdminEntity adminEntity) {
        return modelMapper.map(adminEntity, Admin.class);
    }

    public AdminEntity convert(Admin admin) {
        return modelMapper.map(admin, AdminEntity.class);
    }

}
