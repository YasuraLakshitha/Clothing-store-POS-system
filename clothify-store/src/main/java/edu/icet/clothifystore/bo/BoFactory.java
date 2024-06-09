package edu.icet.clothifystore.bo;

import edu.icet.clothifystore.bo.custom.admin.impl.BoAdminServiceImpl;
import edu.icet.clothifystore.bo.custom.category.impl.BoCategoryServiceImpl;
import edu.icet.clothifystore.bo.custom.employee.impl.BoEmployeeServiceImpl;
import edu.icet.clothifystore.bo.custom.item.impl.BoItemServiceImpl;
import edu.icet.clothifystore.bo.custom.product.impl.BoProductServiceImpl;
import edu.icet.clothifystore.bo.custom.supplier.impl.BoSupplierServiceImpl;
import edu.icet.clothifystore.util.BoType;

public class BoFactory {
    public <T extends SupperBo> T createBoImpl(BoType boType) {
        switch (boType) {
            case ADMIN:
                return (T) new BoAdminServiceImpl();
            case EMPLOYEE:
                return (T) new BoEmployeeServiceImpl();
            case SUPPLIER:
                return (T) new BoSupplierServiceImpl();
            case ITEM:
                return (T) new BoItemServiceImpl();
            case CATEGORY:
                return (T) new BoCategoryServiceImpl();
            case PRODUCT:
                return (T) new BoProductServiceImpl();
            default:
                throw new RuntimeException("Unsupported bo type: " + boType);
        }
    }
}
