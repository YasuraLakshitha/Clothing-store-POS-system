package edu.icet.clothify.bo.custom.payment_type.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.icet.clothify.bo.custom.payment_type.BoPaymentTypeService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.payment_type.PaymentTypeRepository;
import edu.icet.clothify.entity.PaymentTypeEntity;
import edu.icet.clothify.model.PaymentType;
import edu.icet.clothify.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BoPaymentTypeServiceImpl implements BoPaymentTypeService {

    private final PaymentTypeRepository repository = new DaoFactory().getDaoImpl(DaoType.PAYMENT_TYPE);

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean save(PaymentType object) {
        return repository.save(modelMapper.map(object, PaymentTypeEntity.class));
    }

    @Override
    public Boolean update(PaymentType object) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(Integer.parseInt(id));
    }

    @Override
    public PaymentType findById(String id) {
        return modelMapper.map(repository.findById(Integer.parseInt(id)), PaymentType.class);
    }

    @Override
    public List<PaymentType> findAll() {
        return repository.findAll().stream()
                .map(paymentTypeEntity -> paymentTypeEntity == null ? new PaymentType() : modelMapper.map(paymentTypeEntity, PaymentType.class))
                .collect(Collectors.toList());
    }

    @Override
    public PaymentType findByType(String value) {
        return modelMapper.map(repository.findByType(value), PaymentType.class);
    }
}
