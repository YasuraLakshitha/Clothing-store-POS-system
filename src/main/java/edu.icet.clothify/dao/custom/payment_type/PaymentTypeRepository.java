package edu.icet.clothify.dao.custom.payment_type;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.PaymentTypeEntity;

import java.util.List;

public interface PaymentTypeRepository extends Crud<PaymentTypeEntity, Integer> {
    List<PaymentTypeEntity> findAll();

    PaymentTypeEntity findByType(String value);
}
