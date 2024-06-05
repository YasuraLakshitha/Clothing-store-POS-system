package edu.icet.clothifystore.bo.custom.admin.impl;

import edu.icet.clothifystore.bo.custom.admin.BoServiceAdmin;
import edu.icet.clothifystore.dao.custom.admin.AdminRepository;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.entity.AdminEntity;
import edu.icet.clothifystore.model.Admin;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BoAdminServiceImpl implements BoServiceAdmin {

    private final AdminRepository repository = new DaoFactory().getDaoImpl(DaoType.ADMIN);
    private final ModelMapper modelMapper = new ModelMapper();

    public BoAdminServiceImpl() {}

    @Override
    public Boolean save(Admin admin) {
         repository.save(modelMapper.map(admin, AdminEntity.class));
         return true;
    }

    @Override
    public Boolean update(String id) {
        //TODO Update Admin
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO Delete Admin
        return null;
    }

    @Override
    public Admin findById(String id) {
        return modelMapper.map(repository.findById(id),Admin.class);
    }

    @Override
    public List<Admin> findAll() {
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
}
