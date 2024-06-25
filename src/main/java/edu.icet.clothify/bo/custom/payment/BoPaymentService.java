package edu.icet.clothify.bo.custom.payment;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Payment;

public interface BoPaymentService extends Service<Payment> {
    Payment getLast();
}
