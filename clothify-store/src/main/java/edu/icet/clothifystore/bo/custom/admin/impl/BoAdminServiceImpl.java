package edu.icet.clothifystore.bo.custom.admin.impl;

import edu.icet.clothifystore.bo.custom.admin.BoServiceAdmin;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.admin.AdminRepository;
import edu.icet.clothifystore.entity.AdminEntity;
import edu.icet.clothifystore.entity.EmployeeEntity;
import edu.icet.clothifystore.model.Admin;
import edu.icet.clothifystore.model.Employee;
import edu.icet.clothifystore.model.Item;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BoAdminServiceImpl implements BoServiceAdmin {

    private final AdminRepository repository = new DaoFactory().getDaoImpl(DaoType.ADMIN);
    private final ModelMapper modelMapper = new ModelMapper();

    public BoAdminServiceImpl() {}

    @Override
    public Boolean save(Admin admin) {
        // TODO Inverse mapping is not completed
         return repository.save(modelMapper.map(admin, AdminEntity.class));
    }

    @Override
    public Boolean update(Admin object) {
        Set<EmployeeEntity> employeeEntitySet = new HashSet<>();

        for (Employee employee : object.getEmployeeSet()) {
            EmployeeEntity employeeEntity = modelMapper.map(employee, EmployeeEntity.class);
            employeeEntity.setAdminEntity(modelMapper.map(object, AdminEntity.class));
            employeeEntitySet.add(employeeEntity);
        }

        AdminEntity adminEntity = modelMapper.map(object, AdminEntity.class);
        adminEntity.setEmployeeEntitySet(employeeEntitySet);
        return repository.update(adminEntity);
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Admin findById(String id) {

        //TODO Inverse mapping is not completed

        return modelMapper.map(repository.findById(id),Admin.class);
    }

    @Override
    public List<Admin> findAll() {

        //TODO mapping is not completed

        List<Admin> adminList = new ArrayList<>();
        List<AdminEntity> adminEntityList = repository.findAllAdmins();
        adminEntityList.forEach(adminEntity ->{
            adminList.add(modelMapper.map(adminEntity,Admin.class));
        });
        return adminList;
    }

    @Override
    public String generateAdminId(){
        List<Admin> adminList = findAll();
        Pattern pattern = Pattern.compile("AD([0-9]{3})");
        Matcher matcher = pattern.matcher(adminList.get(adminList.size()-1).getId());
        if(matcher.find()) {
            int adminId = Integer.parseInt(matcher.group(1));
            return String.format("AD%03d", ++adminId);
        }
        return "AD001";
    }

    @Override
    public List<String> loadAdminIds() {
        List<String> adminIds = new ArrayList<>();
        List<AdminEntity> adminEntityList = repository.findAllAdmins();
        adminEntityList.forEach(adminEntity ->{
            adminIds.add(adminEntity.getId());
        });
        return adminIds;
    }

    @Override
    public Admin findAdminByEmailAndPassword(String password, String text) {

        //TODO inverse mapping incomplete

        return modelMapper.map(repository.findByEmailAndPassword(password, password),Admin.class);

    }

    @Override
    public Admin findAdminByEmail(String email) {
        AdminEntity adminEntity = repository.findAdminByEmail(email);
        Admin admin = modelMapper.map(adminEntity, Admin.class);
        Set<Employee> employeeSet = new HashSet<>();
        Set<Item> itemSet = new HashSet<>();

        adminEntity.getEmployeeEntitySet().forEach(employeeEntity ->{
            employeeSet.add(modelMapper.map(employeeEntity,Employee.class));
        });
        admin.setEmployeeSet(employeeSet);
        return admin;
    }

}
