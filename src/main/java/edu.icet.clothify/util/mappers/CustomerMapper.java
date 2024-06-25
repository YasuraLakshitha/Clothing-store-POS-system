package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.model.Customer;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.stream.Collectors;

public class CustomerMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    private final OrderMapper orderMapper = new OrderMapper();

    public CustomerMapper() {
        customerMappingConfig();
    }

    private void customerMappingConfig() {
        TypeMap<Customer, CustomerEntity> customerEntityTypeMap = modelMapper.createTypeMap(
                Customer.class,
                CustomerEntity.class
        );
        customerEntityTypeMap.addMappings(mapping -> {
            mapping.map(Customer::getId, CustomerEntity::setId);
            mapping.map(Customer::getCustomerName, CustomerEntity::setCustomerName);
            mapping.map(Customer::getCustomerEmail, CustomerEntity::setCustomerEmail);
            mapping.map(Customer::getCustomerContact, CustomerEntity::setCustomerContact);
            mapping.map(source -> source.getOrderSet() != null ? source.getOrderSet().stream()
                            .map(orderMapper::convert).collect(Collectors.toSet()) : null
                    , CustomerEntity::setOrderEntitySet);
        });
        TypeMap<CustomerEntity, Customer> typeMap = modelMapper.createTypeMap(CustomerEntity.class, Customer.class);
        typeMap.addMappings(mapping -> {
            mapping.map(CustomerEntity::getId, Customer::setId);
            mapping.map(CustomerEntity::getCustomerName, Customer::setCustomerName);
            mapping.map(CustomerEntity::getCustomerEmail, Customer::setCustomerEmail);
            mapping.map(CustomerEntity::getCustomerContact, Customer::setCustomerContact);
            mapping.map(source -> source.getOrderEntitySet() != null ?
                    source.getOrderEntitySet().stream()
                            .map(orderMapper::convert)
                            .collect(Collectors.toSet()) : null, Customer::setOrderSet);
        });
    }

    public Customer convert(CustomerEntity customerEntity) {
        return customerEntity != null ? modelMapper.map(customerEntity, Customer.class) : null;
    }

    public CustomerEntity convert(Customer customer) {
        return modelMapper.map(customer, CustomerEntity.class);
    }
}
