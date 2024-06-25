package edu.icet.clothify.bo.custom.customer.impl;

import edu.icet.clothify.bo.custom.customer.BoCustomerService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.customer.CustomerRepository;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.model.Customer;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.CustomerMapper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BoCustomerServiceImpl implements BoCustomerService {

    private final CustomerRepository repository = new DaoFactory().getDaoImpl(DaoType.CUSTOMER);

    private final CustomerMapper customerMapper = new CustomerMapper();

    @Override
    public Boolean save(Customer object) {
        return repository.save(customerMapper.convert(object));
    }

    @Override
    public Boolean update(Customer object) {
        return repository.update(customerMapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Customer findById(String id) {
        return customerMapper.convert(repository.findById(id));
    }

    @Override
    public String generateCustomerId() {
        List<CustomerEntity> customerList = repository.getAll();
        if (!customerList.isEmpty()) {
            Pattern pattern = Pattern.compile("(C)([0-9]{3})");
            Matcher matcher = pattern.matcher(customerList.get(customerList.size() - 1).getId());
            if (matcher.find())
                return String.format("%s%03d", matcher.group(1), Integer.parseInt(matcher.group(2)) + 1);
        }
        return "C001";
    }

    @Override
    public List<Customer> findAll() {
        return repository.getAll().stream().map(customerMapper::convert).collect(Collectors.toList());
    }

}
