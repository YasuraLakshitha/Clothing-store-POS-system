package edu.icet.clothifystore.dao.custom.product.impl;


import edu.icet.clothifystore.dao.custom.product.ProductRepository;
import edu.icet.clothifystore.entity.ProductEntity;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public Boolean save(ProductEntity productEntity) {
        //TODO: save product
        //TODO: update inventory
        return null;
    }

    @Override
    public ProductEntity findById(String id) {
        //TODO: find by id
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: delete by id
        return null;
    }

    @Override
    public Boolean update(ProductEntity object) {
        //TODO update product data
        return null;
    }

    @Override
    public List<ProductEntity> findAll() {
        session.beginTransaction();
        List<ProductEntity> prodcutEntityList = session.createQuery("from product", ProductEntity.class).getResultList();
        session.getTransaction().commit();
        return prodcutEntityList;
    }
}
