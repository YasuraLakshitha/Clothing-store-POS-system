package edu.icet.clothify.dao.custom.product.impl;


import edu.icet.clothify.dao.custom.product.ProductRepository;
import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.util.CategoryType;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;
import java.util.Stack;

public class ProductRepositoryImpl implements ProductRepository {

    @Override
    public List<ProductEntity> findByCategoryType(CategoryType value) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<ProductEntity> productEntityList = session.createQuery(
                            "from product where categoryEntity.categoryType = : categoryType", ProductEntity.class)
                    .setParameter("categoryType", value.toString()).list();
            session.getTransaction().commit();
            return productEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductEntity findByType(String type) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ProductEntity productEntity = session.createQuery(
                            "from product where productName = : productName", ProductEntity.class)
                    .setParameter("productName", type).getSingleResult();
            session.getTransaction().commit();
            return productEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<ProductEntity> productEntityList = session.createQuery(
                    "from product", ProductEntity.class).list();
            session.getTransaction().commit();
            return productEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductEntity> findByItem(ItemEntity itemEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<ProductEntity> productEntityList = session.createQuery(
                            "from product where productName = : productName", ProductEntity.class)
                    .setParameter("productName", itemEntity.getProductEntity().getProductName()).list();
            session.getTransaction().commit();
            return productEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean save(ProductEntity productEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(productEntity);
            session.getTransaction().commit();
            return session.contains(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ProductEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            session.getTransaction().commit();
            return productEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ProductEntity productEntity = session.get(ProductEntity.class, id);
            if (productEntity != null)
                session.remove(productEntity);
            session.getTransaction().commit();
            return !session.contains(productEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(ProductEntity object) {
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
}
