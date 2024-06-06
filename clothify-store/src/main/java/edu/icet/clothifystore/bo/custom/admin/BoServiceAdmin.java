package edu.icet.clothifystore.bo.custom.admin;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Admin;

import java.util.List;

public interface BoServiceAdmin extends Service<Admin> {
    List<Admin> findAll();
    String generateAdminId();
    List<String> loadAdminIds();
    Admin findAdminByEmailAndPassword(String password, String text);

    Admin findAdminByEmail(String email);

}
