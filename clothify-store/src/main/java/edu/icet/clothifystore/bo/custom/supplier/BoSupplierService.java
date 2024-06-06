package edu.icet.clothifystore.bo.custom.supplier;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Supplier;

import java.util.List;

public interface BoSupplierService extends Service<Supplier> {
    List<Supplier> findAll();

    String generateSupplierId();

    Supplier findSupplierByEmail(String email);
}
