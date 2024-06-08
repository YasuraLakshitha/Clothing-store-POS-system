package edu.icet.clothifystore.dao.custom.category.impl;

import edu.icet.clothifystore.dao.custom.category.CategoryRepository;
import edu.icet.clothifystore.entity.CategoryEntity;
import edu.icet.clothifystore.util.BoType;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

public class CategoryRepositoryImpl implements CategoryRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public CategoryEntity findByType(BoType type) {
        session.beginTransaction();
        CategoryEntity categoryType = session.createQuery("from category where categoryType = :categoryType", CategoryEntity.class)
                .setParameter("categoryType", type.toString())
                .getSingleResult();
        session.getTransaction().commit();
        return categoryType;
    }

    @Override
    public Boolean save(CategoryEntity categoryEntity) {
        //TODO: persist category
        return null;
    }

    @Override
    public CategoryEntity findById(String id) {
        //TODO: find by id
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: Delete by id
        return null;
    }

    @Override
    public Boolean update(CategoryEntity object) {
        //TODO: update category
        return null;
    }
}
