package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.model.Employee;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Order;
import edu.icet.clothify.model.OrderDetails;
import edu.icet.clothify.util.config.OrderDetailsIdGenerator;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.spi.MappingContext;

public class OrderDetailsMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    public OrderDetailsMapper() {
        mappingConfig();
    }

    // private OrderMapper orderMapper = new OrderMapper();

    private void mappingConfig() {
//        TypeMap<OrderDetails, OrderDetailsEntity> typeMap = modelMapper.createTypeMap(OrderDetails.class, OrderDetailsEntity.class);
//        typeMap.addMappings(mapping -> {
//            mapping.map(source -> {
//                if (source.getId() == null) return null;
//                OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
//                orderDetailsEntity.setId(new OrderDetailsIdGenerator(
//                        source.getId().getOrderId(), source.getId().getEmployeeId(), source.getId().getItemId()));
//                return orderDetailsEntity.getId();
//            }, OrderDetailsEntity::setId);
//            mapping.map(OrderDetails::getItem, OrderDetailsEntity::setOrderEntity);
//            mapping.map(OrderDetails::getItem, OrderDetailsEntity::setItemEntity);
//            mapping.map(OrderDetails::getItem, OrderDetailsEntity::setEmployeeEntity);
//        });

        Converter<OrderDetails, OrderDetailsEntity> orderDetailsConverter = new Converter<OrderDetails, OrderDetailsEntity>() {
            @Override
            public OrderDetailsEntity convert(MappingContext<OrderDetails, OrderDetailsEntity> context) {
                OrderDetails orderDetails = context.getSource();
                OrderDetailsEntity orderDetailsEntity = new OrderDetailsEntity();
                orderDetailsEntity.setId(orderDetails.getId());
                orderDetailsEntity.setItemEntity(modelMapper.map(orderDetails.getItem(), ItemEntity.class));
                orderDetailsEntity.setEmployeeEntity(modelMapper.map(orderDetails.getEmployee(), EmployeeEntity.class));
                orderDetailsEntity.setOrderEntity(modelMapper.map(orderDetails.getOrder(), OrderEntity.class));
                return orderDetailsEntity;
            }
        };
        modelMapper.addConverter(orderDetailsConverter);

//        TypeMap<OrderDetailsEntity, OrderDetails> inverseTypeMap = modelMapper.createTypeMap(OrderDetailsEntity.class, OrderDetails.class);
//        inverseTypeMap.addMappings(mapping -> {
//            mapping.map(OrderDetailsEntity::getId, (OrderDetails destination, OrderDetailsIdGenerator value) -> {
//                if (value == null) {
//                    return;
//                }
//                destination.getId().setOrderId(value.getOrderId());
//                destination.getId().setItemId(value.getItemId());
//                destination.getId().setEmployeeId(value.getEmployeeId());
//            });
//            mapping.map(OrderDetailsEntity::getItemEntity, OrderDetails::setOrder);
//            mapping.map(OrderDetailsEntity::getItemEntity, OrderDetails::setItem);
//            mapping.map(OrderDetailsEntity::getItemEntity, OrderDetails::setEmployee);
//        });


        Converter<OrderDetailsEntity, OrderDetails> orderDetailsEntityConverter = new Converter<OrderDetailsEntity, OrderDetails>() {
            @Override
            public OrderDetails convert(MappingContext<OrderDetailsEntity, OrderDetails> context) {
                OrderDetailsEntity orderDetailsEntity = context.getSource();
                OrderDetails orderDetails = new OrderDetails();

                orderDetails.setId(orderDetailsEntity.getId());
                orderDetails.setItem(modelMapper.map(orderDetailsEntity.getOrderEntity(), Item.class));
                orderDetails.setEmployee(modelMapper.map(orderDetailsEntity.getOrderEntity(), Employee.class));
                orderDetails.setOrder(modelMapper.map(orderDetailsEntity.getOrderEntity(), Order.class));
                return orderDetails;
            }
        };
        modelMapper.addConverter(orderDetailsConverter);
    }


    public OrderDetails convert(OrderDetailsEntity entity) {
        return modelMapper.map(entity, OrderDetails.class);
    }

    public OrderDetailsEntity convert(OrderDetails dto) {
        return modelMapper.map(dto, OrderDetailsEntity.class);
    }

}
