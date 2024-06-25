package edu.icet.clothify.dao.custom.supplier;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.SupplierEntity;

import java.util.List;

public interface SupplierRepository extends Crud<SupplierEntity, String> {
    List<SupplierEntity> getAllSuppliers();

    SupplierEntity findByEmail(String email);
}
