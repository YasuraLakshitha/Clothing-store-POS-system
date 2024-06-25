package edu.icet.clothify.bo.custom.admin.impl;

import edu.icet.clothify.bo.custom.admin.BoServiceAdmin;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.admin.AdminRepository;
import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.model.Admin;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.AdminMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class BoAdminServiceImpl implements BoServiceAdmin {

    private final AdminRepository repository = new DaoFactory().getDaoImpl(DaoType.ADMIN);

    private final AdminMapper adminMapper = new AdminMapper();

    @Override
    public Boolean save(Admin admin) {
        return repository.save(adminMapper.convert(admin));
    }

    @Override
    public Boolean update(Admin object) {
        return repository.update(adminMapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Admin findById(String id) {
        return adminMapper.convert(repository.findById(id));
    }

    @Override
    public List<Admin> findAll() {
        List<AdminEntity> adminEntityList = repository.findAllAdmins();
        List<Admin> adminList = new ArrayList<>();
        for (AdminEntity adminEntity : adminEntityList)
            adminList.add(adminMapper.convert(adminEntity));
        return adminList;
    }

    @Override
    public String generateAdminId() {
        List<Admin> adminList = findAll();
        Pattern pattern = Pattern.compile("AD([0-9]{3})");
        Matcher matcher = pattern.matcher(adminList.get(adminList.size() - 1).getId());
        if (matcher.find()) {
            int adminId = Integer.parseInt(matcher.group(1));
            return String.format("AD%03d", ++adminId);
        }
        return "AD001";
    }

    @Override
    public List<String> loadAdminIds() {
        List<String> adminIds = new ArrayList<>();
        List<AdminEntity> adminEntityList = repository.findAllAdmins();
        adminEntityList.forEach(adminEntity -> {
            adminIds.add(adminEntity.getId());
        });
        return adminIds;
    }

    @Override
    public Admin findAdminByEmailAndPassword(String password, String email) {
        return adminMapper.convert(repository.findByEmailAndPassword(email, password));
    }

    private Admin getAdmin(AdminEntity adminEntity) {
        return adminMapper.convert(adminEntity);
    }

    @Override
    public Admin findAdminByEmail(String email) {
        return adminMapper.convert(repository.findAdminByEmail(email));
    }

}
