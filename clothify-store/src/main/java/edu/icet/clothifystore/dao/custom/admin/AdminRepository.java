package edu.icet.clothifystore.dao.custom.admin;

import edu.icet.clothifystore.dao.Crud;
import edu.icet.clothifystore.entity.AdminEntity;
import java.util.List;


public interface AdminRepository  extends Crud<AdminEntity,String> {
    List<AdminEntity> findAllAdmins();
}
