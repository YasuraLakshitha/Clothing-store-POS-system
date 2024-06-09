package edu.icet.clothifystore.dao.custom.product.impl;


import edu.icet.clothifystore.dao.custom.product.ProductRepository;
import edu.icet.clothifystore.entity.ProductEntity;
import edu.icet.clothifystore.util.CategoryType;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProductRepositoryImpl implements ProductRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public List<ProductEntity> findByCategoryType(Enum<CategoryType> value) {

        System.out.println(value.toString());

        session.beginTransaction();
        List<ProductEntity> productEntityList = session.createQuery("from product where categoryEntity.categoryType = : categoryType", ProductEntity.class)
                .setParameter("categoryType", value.toString()).list();
        session.getTransaction().commit();
        return productEntityList;
    }

    @Override
    public ProductEntity findByType(String type) {
        session.beginTransaction();
        ProductEntity productEntity = session.createQuery("from product where productName = : productName", ProductEntity.class)
                .setParameter("productName", type).getSingleResult();
        session.getTransaction().commit();
        return productEntity;
    }

    @Override
    public Boolean save(ProductEntity productEntity) {
        //TODO:save product
        return null;
    }

    @Override
    public ProductEntity findById(String id) {
        //TODO:fond product by id
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO:delete product
        return null;
    }

    @Override
    public Boolean update(ProductEntity object) {
        //TODO:update product
        return null;
    }
}
