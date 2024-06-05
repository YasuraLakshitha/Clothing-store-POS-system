package edu.icet.clothifystore.dao.custom.supplier.impl;

import edu.icet.clothifystore.dao.custom.supplier.SupplierRepository;
import edu.icet.clothifystore.entity.SupplierEntity;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SupplierRepositoryImpl implements SupplierRepository {
    private final Session session = HibernateUtil.getSession();
    @Override
    public Boolean save(SupplierEntity supplierEntity) {
        session.beginTransaction();
        session.persist(supplierEntity);
        session.getTransaction().commit();
        return session.contains(session.get(SupplierEntity.class, supplierEntity.getId()));
    }

    @Override
    public SupplierEntity findById(String id) {
        session.beginTransaction();
        SupplierEntity supplierEntity = session.get(SupplierEntity.class, id);
        session.getTransaction().commit();
        return supplierEntity;
    }

    @Override
    public Boolean delete(String id) {
        session.beginTransaction();
        session.remove(session.get(SupplierEntity.class, id));
        session.getTransaction().commit();
        return !session.contains(session.get(SupplierEntity.class, id));
    }

    @Override
    public Boolean update(String id) {
        return null;
    }

    @Override
    public List<SupplierEntity> getAllSuppliers() {
        session.beginTransaction();
        List<SupplierEntity> supplierEntityList = session.createQuery(
                "from supplier", SupplierEntity.class).list();
        session.getTransaction().commit();
        return supplierEntityList;
    }
}
