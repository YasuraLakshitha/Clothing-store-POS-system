package edu.icet.clothify.dao.custom.supplier.impl;

import edu.icet.clothify.dao.custom.supplier.SupplierRepository;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {
    private final Session session = HibernateUtil.getSession();

    @Override
    public Boolean save(SupplierEntity supplierEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(supplierEntity);
            session.getTransaction().commit();
            return session.contains(session.get(SupplierEntity.class, supplierEntity.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public SupplierEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
            session.getTransaction().commit();
            return supplierEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.remove(session.get(SupplierEntity.class, id));
            session.getTransaction().commit();
            return !session.contains(session.get(SupplierEntity.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(SupplierEntity object) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.merge(object);
            session.getTransaction().commit();
            return session.contains(session.get(SupplierEntity.class, object.getId()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<SupplierEntity> getAllSuppliers() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<SupplierEntity> supplierEntityList = session.createQuery(
                    "from supplier", SupplierEntity.class).list();
            session.getTransaction().commit();
            return supplierEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public SupplierEntity findByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            SupplierEntity supplierEntity = session.createQuery(
                            "from supplier where supplierEmail = :email", SupplierEntity.class)
                    .setParameter("email", email)
                    .uniqueResult();
            session.getTransaction().commit();
            return supplierEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
