package edu.icet.clothify.dao.custom.payment.impl;

import edu.icet.clothify.dao.custom.payment.PaymentRepository;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.entity.PaymentEntity;
import edu.icet.clothify.util.HibernateUtil;
import jakarta.transaction.Transactional;
import org.hibernate.Session;

import java.util.List;


public class PaymentRepositoryImpl implements PaymentRepository {


    @Override
    public Boolean save(PaymentEntity paymentEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.createQuery("insert into payment (payment ,payment_type ) values" +
                            " (:payment , :payment_type)", PaymentEntity.class)
                    .setParameter("payment", paymentEntity)
                    .setParameter(":payment_type", paymentEntity.getPaymentTypeEntity());
            session.getTransaction().commit();
            return session.contains(paymentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PaymentEntity findById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            PaymentEntity paymentEntity = session.get(PaymentEntity.class, id);
            session.getTransaction().commit();
            return paymentEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            PaymentEntity paymentEntity = session.get(PaymentEntity.class, id);
            session.remove(paymentEntity);
            session.getTransaction().commit();
            return !session.contains(paymentEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(PaymentEntity object) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.createQuery("update payment set orderEntity = :orderEntity where id = :id")
                    .setParameter("orderEntity", object.getOrderEntity())
                    .setParameter("id", object.getId()).executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PaymentEntity getLast() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<PaymentEntity> paymentList = session.createQuery("from payment", PaymentEntity.class).list();
            session.getTransaction().commit();
            return paymentList.get(paymentList.size() - 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
