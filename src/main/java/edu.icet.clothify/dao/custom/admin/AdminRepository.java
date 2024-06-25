package edu.icet.clothify.dao.custom.admin;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.AdminEntity;

import java.util.List;


public interface AdminRepository extends Crud<AdminEntity, String> {
    List<AdminEntity> findAllAdmins();

    AdminEntity findByEmailAndPassword(String email, String password);

    AdminEntity findAdminByEmail(String email);
}
