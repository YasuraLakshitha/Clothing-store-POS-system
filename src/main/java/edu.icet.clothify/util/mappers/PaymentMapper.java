package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.entity.PaymentEntity;
import edu.icet.clothify.model.Payment;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class PaymentMapper {
    private final ModelMapper modelMapper;

    public PaymentMapper() {
        this.modelMapper = new ModelMapper();
        mapperConfig();
    }

    private void mapperConfig() {
        TypeMap<Payment, PaymentEntity> paymentPaymentEntityTypeMap = modelMapper.createTypeMap(Payment.class, PaymentEntity.class);
        paymentPaymentEntityTypeMap.addMappings(mapping -> {
            mapping.map(Payment::getPaymentType, PaymentEntity::setPaymentTypeEntity);
            mapping.map(Payment::getNetAmount, PaymentEntity::setNetAmount);
            mapping.map(Payment::getId, PaymentEntity::setId);
            mapping.map(source -> {
                if (source == null) return null;
                if (source.getOrder().getId() == null) return null;
                OrderEntity orderEntity = new OrderEntity();
                orderEntity.setId(source.getOrder().getId());
                return orderEntity;
            }, PaymentEntity::setOrderEntity);
        });

        TypeMap<PaymentEntity, Payment> paymentEntityPaymentTypeMap = modelMapper.createTypeMap(PaymentEntity.class, Payment.class);
        paymentEntityPaymentTypeMap.addMappings(mapping -> {
            mapping.map(PaymentEntity::getNetAmount, Payment::setNetAmount);
            mapping.map(PaymentEntity::getId, Payment::setId);
        });
    }

    public PaymentEntity convert(Payment payment) {
        return modelMapper.map(payment, PaymentEntity.class);
    }

    public Payment convert(PaymentEntity paymentEntity) {
        return modelMapper.map(paymentEntity, Payment.class);
    }


}
