package edu.icet.clothify.bo.custom.order_details.impl;

import edu.icet.clothify.bo.custom.order_details.BoOrderDetailsService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.order_details.OrderDetailsRepository;
import edu.icet.clothify.model.Order;
import edu.icet.clothify.model.OrderDetails;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.OrderDetailsMapper;
import edu.icet.clothify.util.mappers.OrderMapper;

import java.util.List;
import java.util.stream.Collectors;

public class BoOrderDetailsServiceImpl implements BoOrderDetailsService {

    private final OrderDetailsRepository repository = new DaoFactory().getDaoImpl(DaoType.ORDER_DETAILS);

    private final OrderDetailsMapper mapper = new OrderDetailsMapper();

    @Override
    public Boolean save(OrderDetails object) {
        return repository.save(mapper.convert(object));
    }

    @Override
    public Boolean update(OrderDetails object) {
        return repository.update(mapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public OrderDetails findById(String id) {
        return mapper.convert(repository.findById(id));
    }

    @Override
    public List<OrderDetails> findByOrder(Order order) {
        return (repository.findByOrder(new OrderMapper().convert(order)).stream()
                .map(mapper::convert).collect(Collectors.toList()));
    }
}

