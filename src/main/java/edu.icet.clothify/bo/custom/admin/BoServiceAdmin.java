package edu.icet.clothify.bo.custom.admin;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Admin;

import java.util.List;

public interface BoServiceAdmin extends Service<Admin> {
    List<Admin> findAll();

    String generateAdminId();

    List<String> loadAdminIds();

    Admin findAdminByEmailAndPassword(String password, String text);

    Admin findAdminByEmail(String email);

}
