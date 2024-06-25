package edu.icet.clothify.dao.custom.order_details.impl;

import edu.icet.clothify.dao.custom.order_details.OrderDetailsRepository;
import edu.icet.clothify.entity.OrderDetailsEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class OrderDetailsRepositoryImpl implements OrderDetailsRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public Boolean save(OrderDetailsEntity orderDetailsEntity) {
        System.out.println(orderDetailsEntity);
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.merge(orderDetailsEntity);
            session.getTransaction().commit();
            return session.contains(orderDetailsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public OrderDetailsEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            OrderDetailsEntity orderDetailsEntity = session.get(OrderDetailsEntity.class, id);
            session.getTransaction().commit();
            return orderDetailsEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            OrderDetailsEntity orderDetailsEntity = session.get(OrderDetailsEntity.class, id);
            session.remove(orderDetailsEntity);
            return !session.contains(orderDetailsEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(OrderDetailsEntity object) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.merge(object);
            session.getTransaction().commit();
            return session.contains(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetailsEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<OrderDetailsEntity> orderDetailsEntityList = session.createQuery(
                    "from order_details ", OrderDetailsEntity.class).list();
            session.getTransaction().commit();
            return orderDetailsEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<OrderDetailsEntity> findByOrder(OrderEntity orderEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<OrderDetailsEntity> orderDetailsEntityList = session.createQuery(
                            "from order_details where orderEntity =:order_id", OrderDetailsEntity.class)
                    .setParameter("order_id", orderEntity).list();
            session.getTransaction().commit();
            System.out.println(orderDetailsEntityList);
            return orderDetailsEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
