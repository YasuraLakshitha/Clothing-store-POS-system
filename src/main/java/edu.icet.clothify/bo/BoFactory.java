package edu.icet.clothify.bo;

import edu.icet.clothify.bo.custom.admin.impl.BoAdminServiceImpl;
import edu.icet.clothify.bo.custom.category.impl.BoCategoryServiceImpl;
import edu.icet.clothify.bo.custom.customer.impl.BoCustomerServiceImpl;
import edu.icet.clothify.bo.custom.employee.impl.BoEmployeeServiceImpl;
import edu.icet.clothify.bo.custom.item.impl.BoItemServiceImpl;
import edu.icet.clothify.bo.custom.order.impl.BoOrderServiceImpl;
import edu.icet.clothify.bo.custom.order_details.impl.BoOrderDetailsServiceImpl;
import edu.icet.clothify.bo.custom.payment.impl.BoPaymentServiceImpl;
import edu.icet.clothify.bo.custom.payment_type.impl.BoPaymentTypeServiceImpl;
import edu.icet.clothify.bo.custom.product.impl.BoProductServiceImpl;
import edu.icet.clothify.bo.custom.supplier.impl.BoSupplierServiceImpl;
import edu.icet.clothify.util.BoType;

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
            case ORDER:
                return (T) new BoOrderServiceImpl();
            case CUSTOMER:
                return (T) new BoCustomerServiceImpl();
            case PAYMENT:
                return (T) new BoPaymentServiceImpl();
            case PAYMENT_TYPE:
                return (T) new BoPaymentTypeServiceImpl();
            case ORDER_DETAILS:
                return (T) new BoOrderDetailsServiceImpl();
            default:
                throw new RuntimeException("Unsupported bo type: " + boType);
        }
    }
}
