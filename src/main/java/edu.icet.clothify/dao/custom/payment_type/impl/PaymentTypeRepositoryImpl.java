package edu.icet.clothify.dao.custom.payment_type.impl;

import edu.icet.clothify.dao.custom.payment_type.PaymentTypeRepository;
import edu.icet.clothify.entity.PaymentTypeEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PaymentTypeRepositoryImpl implements PaymentTypeRepository {

    @Override
    public Boolean save(PaymentTypeEntity paymentTypeEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(paymentTypeEntity);
            session.getTransaction().commit();
            return session.contains(paymentTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public PaymentTypeEntity findById(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            PaymentTypeEntity paymentTypeEntity = session.get(PaymentTypeEntity.class, id);
            session.getTransaction().commit();
            return paymentTypeEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(Integer id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            PaymentTypeEntity paymentTypeEntity = session.get(PaymentTypeEntity.class, id);
            session.remove(paymentTypeEntity);
            return !session.contains(paymentTypeEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(PaymentTypeEntity object) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.merge(object);
            session.getTransaction().commit();
            return session.contains(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PaymentTypeEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<PaymentTypeEntity> paymentTypeEntities = session.createQuery("from payment_type ", PaymentTypeEntity.class).list();
            session.getTransaction().commit();
            return paymentTypeEntities;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PaymentTypeEntity findByType(String value) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            return session.createQuery(
                            "from payment_type where type = :type ", PaymentTypeEntity.class)
                    .setParameter("type", value).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
