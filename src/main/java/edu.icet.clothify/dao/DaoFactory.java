package edu.icet.clothify.dao;


import edu.icet.clothify.dao.custom.admin.impl.AdminRepositoryImpl;
import edu.icet.clothify.dao.custom.category.impl.CategoryRepositoryImpl;
import edu.icet.clothify.dao.custom.customer.impl.CustomerRepositoryImpl;
import edu.icet.clothify.dao.custom.employee.impl.EmployeeRepositoryImpl;
import edu.icet.clothify.dao.custom.item.impl.ItemRepositoryImpl;
import edu.icet.clothify.dao.custom.order.impl.OrderRepositoryImpl;
import edu.icet.clothify.dao.custom.order_details.impl.OrderDetailsRepositoryImpl;
import edu.icet.clothify.dao.custom.payment.impl.PaymentRepositoryImpl;
import edu.icet.clothify.dao.custom.payment_type.impl.PaymentTypeRepositoryImpl;
import edu.icet.clothify.dao.custom.product.impl.ProductRepositoryImpl;
import edu.icet.clothify.dao.custom.supplier.impl.SupplierRepositoryImpl;
import edu.icet.clothify.util.DaoType;

public class DaoFactory {
    public <T extends SupperDao> T getDaoImpl(DaoType type) {
        switch (type) {
            case ADMIN:
                return (T) new AdminRepositoryImpl();
            case EMPLOYEE:
                return (T) new EmployeeRepositoryImpl();
            case SUPPLIER:
                return (T) new SupplierRepositoryImpl();
            case ITEM:
                return (T) new ItemRepositoryImpl();
            case PRODUCT:
                return (T) new ProductRepositoryImpl();
            case CATEGORY:
                return (T) new CategoryRepositoryImpl();
            case ORDER:
                return (T) new OrderRepositoryImpl();
            case CUSTOMER:
                return (T) new CustomerRepositoryImpl();
            case PAYMENT:
                return (T) new PaymentRepositoryImpl();
            case PAYMENT_TYPE:
                return (T) new PaymentTypeRepositoryImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailsRepositoryImpl();
        }
        return null;
    }
}
