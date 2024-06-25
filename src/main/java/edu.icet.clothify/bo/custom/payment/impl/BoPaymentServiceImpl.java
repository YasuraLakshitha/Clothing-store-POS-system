package edu.icet.clothify.bo.custom.payment.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.bo.custom.payment.BoPaymentService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.payment.PaymentRepository;
import edu.icet.clothify.entity.PaymentEntity;
import edu.icet.clothify.model.Payment;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.PaymentMapper;


public class BoPaymentServiceImpl implements BoPaymentService {

    private final PaymentRepository repository = new DaoFactory().getDaoImpl(DaoType.PAYMENT);

    private final PaymentMapper mapper = new PaymentMapper();

    @Override
    public Boolean save(Payment object) {
        return repository.save(mapper.convert(object));
    }

    @Override
    public Boolean update(Payment object) {
        return repository.update(mapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(Integer.parseInt(id));
    }

    @Override
    public Payment findById(String id) {
        return mapper.convert(repository.findById(Integer.parseInt(id)));
    }

    @Override
    public Payment getLast() {
        PaymentEntity paymentEntity = repository.getLast();
        return paymentEntity != null ? mapper.convert(paymentEntity) : new Payment();
    }
}
