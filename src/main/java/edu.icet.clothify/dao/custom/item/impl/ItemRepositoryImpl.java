package edu.icet.clothify.dao.custom.item.impl;


import edu.icet.clothify.dao.custom.item.ItemRepository;
import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {


    @Override
    public Boolean save(ItemEntity itemEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(itemEntity);
            session.getTransaction().commit();
            return session.contains(itemEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public ItemEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ItemEntity itemEntity = (ItemEntity) session.get(ItemEntity.class, id);
            session.getTransaction().commit();
            return itemEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ItemEntity itemEntity = session.get(ItemEntity.class, id);
            if (itemEntity != null) session.remove(itemEntity);
            return !session.contains(itemEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Boolean update(ItemEntity object) {
        System.out.println(object);
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ItemEntity itemEntity = session.get(ItemEntity.class, object.getId());
            object.setProductEntity(itemEntity.getProductEntity());
            session.merge(object);
            session.getTransaction().commit();
            return session.contains(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ItemEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<ItemEntity> itemEntityList = session.createQuery("from item", ItemEntity.class).getResultList();
            session.getTransaction().commit();
            return itemEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ItemEntity> getByProductType(String type) {

        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<ItemEntity> itemEntityList = session.createQuery(
                            "from item where productEntity.productName =:type", ItemEntity.class)
                    .setParameter("type", type).list();
            session.getTransaction().commit();
            return itemEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ItemEntity find(ItemEntity itemEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            ItemEntity itemEntity1 = session.get(ItemEntity.class, itemEntity.getId());
            session.getTransaction().commit();
            return itemEntity1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
