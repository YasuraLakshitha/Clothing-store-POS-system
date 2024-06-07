package edu.icet.clothifystore.dao;

import edu.icet.clothifystore.dao.custom.admin.impl.AdminRepositoryImpl;
import edu.icet.clothifystore.dao.custom.employee.impl.EmployeeRepositoryImpl;
import edu.icet.clothifystore.dao.custom.product.impl.ProductRepositoryImpl;
import edu.icet.clothifystore.dao.custom.supplier.impl.SupplierRepositoryImpl;
import edu.icet.clothifystore.util.DaoType;

public class DaoFactory {
    public <T>T getDaoImpl(DaoType type) {
        switch (type) {
            case ADMIN:
                return (T) new AdminRepositoryImpl();
            case EMPLOYEE:
                return (T) new EmployeeRepositoryImpl();
            case SUPPLIER:
                return (T) new SupplierRepositoryImpl();
            case PRODUCT:
                return (T) new ProductRepositoryImpl();
        }
        return null;
    }
}
