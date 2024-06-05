package edu.icet.clothifystore.bo;

import edu.icet.clothifystore.bo.custom.admin.impl.BoAdminServiceImpl;
import edu.icet.clothifystore.bo.custom.employee.impl.BoEmployeeServiceImpl;
import edu.icet.clothifystore.bo.custom.supplier.impl.BoSupplierServiceImpl;
import edu.icet.clothifystore.util.BoType;

public class BoFactory {
    public <T>T createBoImpl(BoType boType) {
        switch (boType) {
            case ADMIN:
                return (T) new BoAdminServiceImpl();
            case EMPLOYEE:
                return (T) new BoEmployeeServiceImpl();
            case SUPPLIER:
                return (T) new BoSupplierServiceImpl();
        }
        return null;
    }
}
