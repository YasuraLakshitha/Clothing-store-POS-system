package edu.icet.clothify.dao.custom.order.impl;

import edu.icet.clothify.dao.custom.order.OrderRepository;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderRepositoryImpl implements OrderRepository {
    @Override
    public Boolean save(OrderEntity orderEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(orderEntity);
            session.getTransaction().commit();
            return session.contains(session.get(OrderEntity.class, orderEntity.getId()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public OrderEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            OrderEntity orderEntity = session.get(OrderEntity.class, id);
            session.getTransaction().commit();
            return orderEntity;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            OrderEntity orderEntity = session.get(OrderEntity.class, id);
            session.remove(orderEntity);
            session.getTransaction().commit();
            return !session.contains(session.get(OrderEntity.class, id));
        }
    }

    @Override
    public Boolean update(OrderEntity object) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(object);
            session.getTransaction().commit();
            return session.contains(object);
        }
    }

    @Override
    public List<OrderEntity> findAll() {
        List<OrderEntity> orderEntityList;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            orderEntityList = session.createQuery("from customer_order ", OrderEntity.class).list();
            session.getTransaction().commit();
        }
        return orderEntityList;
    }

    @Override
    public OrderEntity findByCustomer(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            CustomerEntity customerEntity = session.get(CustomerEntity.class, id);
            OrderEntity order = session.createQuery(
                            "from customer_order where customerEntity= :customerEntity", OrderEntity.class)
                    .setParameter("customerEntity", customerEntity).getSingleResult();
            session.getTransaction().commit();
            return order;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
