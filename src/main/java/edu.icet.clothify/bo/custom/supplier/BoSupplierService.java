package edu.icet.clothify.bo.custom.supplier;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Supplier;

import java.util.List;

public interface BoSupplierService extends Service<Supplier> {
    List<Supplier> findAll();

    String generateSupplierId();

    Supplier findSupplierByEmail(String email);
}
