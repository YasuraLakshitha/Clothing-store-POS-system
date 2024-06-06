package edu.icet.clothifystore.dao.custom.supplier;

import edu.icet.clothifystore.dao.Crud;
import edu.icet.clothifystore.entity.SupplierEntity;

import java.util.List;

public interface SupplierRepository extends Crud<SupplierEntity,String> {
    List<SupplierEntity> getAllSuppliers();

    SupplierEntity findByEmail(String email);
}
