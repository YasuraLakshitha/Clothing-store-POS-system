package edu.icet.clothify.dao.custom.customer.impl;

import edu.icet.clothify.dao.custom.customer.CustomerRepository;
import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.beans.Transient;
import java.util.List;

@Slf4j
public class CustomerRepositoryImpl implements CustomerRepository {

    @Override
    public Boolean save(CustomerEntity customerEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(customerEntity);
            session.getTransaction().commit();
            return session.contains(customerEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public CustomerEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            CustomerEntity customerEntity = session.get(CustomerEntity.class, id);
            session.getTransaction().commit();
            return customerEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            CustomerEntity customerEntity = session.get(CustomerEntity.class, id);
            session.remove(customerEntity);
            session.getTransaction().commit();
            return !session.contains(customerEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(CustomerEntity object) {
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
    public List<CustomerEntity> getAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<CustomerEntity> customerEntityList = session.createQuery("from customer ", CustomerEntity.class).list();
            session.getTransaction().commit();
            return customerEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
