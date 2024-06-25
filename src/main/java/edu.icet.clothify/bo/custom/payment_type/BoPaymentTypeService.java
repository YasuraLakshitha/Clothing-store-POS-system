package edu.icet.clothify.bo.custom.payment_type;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.PaymentType;

import java.util.List;

public interface BoPaymentTypeService extends Service<PaymentType> {
    List<PaymentType> findAll();

    PaymentType findByType(String value);

}
