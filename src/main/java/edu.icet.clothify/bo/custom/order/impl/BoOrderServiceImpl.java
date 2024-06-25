package edu.icet.clothify.bo.custom.order.impl;


import edu.icet.clothify.bo.custom.order.BoOrderService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.order.OrderRepository;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.model.Order;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.OrderMapper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BoOrderServiceImpl implements BoOrderService {

    private final OrderRepository orderRepository = new DaoFactory().getDaoImpl(DaoType.ORDER);

    private final OrderMapper orderMapper = new OrderMapper();

    @Override
    public String generateOrderId() {
        List<OrderEntity> orderEntityList = orderRepository.findAll();
        Pattern pattern = Pattern.compile("(OR)([0-9]{3})");
        Matcher matcher = null;
        if (!orderEntityList.isEmpty()) {
            matcher = pattern.matcher(orderEntityList.get(orderEntityList.size() - 1).getId());
            if (matcher.find()) {
                return String.format("%s%03d", matcher.group(1), Integer.parseInt(matcher.group(2)) + 1);
            }
        }
        return "OR001";
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll().stream().map(orderMapper::convert).collect(Collectors.toList());
    }

    @Override
    public Order findByCustomerId(String id) {
        return orderMapper.convert(orderRepository.findByCustomer(id));
    }

    @Override
    public Boolean save(Order object) {
        return orderRepository.save(orderMapper.convert(object));
    }

    @Override
    public Boolean update(Order object) {
        return orderRepository.update(orderMapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return orderRepository.delete(id);
    }

    @Override
    public Order findById(String id) {
        return orderMapper.convert(orderRepository.findById(id));
    }
}
