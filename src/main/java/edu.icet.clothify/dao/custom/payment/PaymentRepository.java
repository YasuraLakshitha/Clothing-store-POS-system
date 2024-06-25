package edu.icet.clothify.dao.custom.payment;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.PaymentEntity;

public interface PaymentRepository extends Crud<PaymentEntity, Integer> {
    PaymentEntity getLast();
}
