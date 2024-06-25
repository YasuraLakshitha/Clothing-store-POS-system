package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Order;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private final ItemMapper itemMapper = new ItemMapper();

    public OrderMapper() {
        mappingConfig();
    }

    private void mappingConfig() {
        TypeMap<Order, OrderEntity> entityTypeMap = modelMapper.createTypeMap(Order.class, OrderEntity.class);
        entityTypeMap.addMappings(mapping -> {
            mapping.map(Order::getId, OrderEntity::setId);
            mapping.map(source -> getMappedItems(source.getItemSet()), OrderEntity::setItemEntitySet);
            mapping.map(Order::getCustomer, OrderEntity::setCustomerEntity);
            mapping.map(Order::getEmployee, OrderEntity::setEmployeeEntity);
        });

        TypeMap<OrderEntity, Order> dtoTypeMap = modelMapper.createTypeMap(OrderEntity.class, Order.class);
        dtoTypeMap.addMappings(mapping -> {
            mapping.map(OrderEntity::getId, Order::setId);
            mapping.map(OrderEntity::getItemEntitySet, (destination, value) -> {
                if (destination.getItemSet() != null) {
                    destination.getItemSet().clear();
                    destination.getItemSet().addAll(mapItemEntitiesToItems((Set<ItemEntity>) value));
                }
            });
        });
    }

    private Set<ItemEntity> getMappedItems(Set<Item> itemSet) {
        return itemSet != null ? itemSet.stream()
                .map(itemMapper::convert).collect(Collectors.toSet()) : new HashSet<ItemEntity>();
    }

    private Set<Item> mapItemEntitiesToItems(Set<ItemEntity> itemEntities) {
        if (itemEntities == null) return null;
        return itemEntities.stream()
                .map(itemMapper::convert)
                .collect(Collectors.toSet());
    }

    public Order convert(OrderEntity orderEntity) {
        return modelMapper.map(orderEntity, Order.class);
    }

    public OrderEntity convert(Order order) {
        return modelMapper.map(order, OrderEntity.class);
    }
}
