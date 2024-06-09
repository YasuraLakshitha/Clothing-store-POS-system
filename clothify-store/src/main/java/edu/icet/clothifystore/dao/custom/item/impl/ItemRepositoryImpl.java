package edu.icet.clothifystore.dao.custom.item.impl;


import edu.icet.clothifystore.dao.custom.item.ItemRepository;
import edu.icet.clothifystore.entity.ItemEntity;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ItemRepositoryImpl implements ItemRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public Boolean save(ItemEntity itemEntity) {
        session.beginTransaction();
        session.persist(itemEntity);
        session.getTransaction().commit();
        //TODO: update inventory
        return null;
    }

    @Override
    public ItemEntity findById(String id) {
        //TODO: find by id
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: delete by id
        return null;
    }

    @Override
    public Boolean update(ItemEntity object) {
        //TODO update item data
        return null;
    }

    @Override
    public List<ItemEntity> findAll() {
        session.beginTransaction();
        List<ItemEntity> itemEntityList = session.createQuery("from item", ItemEntity.class).getResultList();
        session.getTransaction().commit();
        return itemEntityList;
    }

    @Override
    public List<ItemEntity> getByProductType(String type) {
        session.beginTransaction();
        List<ItemEntity> itemEntityList = session.createQuery("from item where productEntity.productName = :productType", ItemEntity.class)
                .setParameter("productType", type).list();
        session.getTransaction().commit();
        return itemEntityList;
    }
}
