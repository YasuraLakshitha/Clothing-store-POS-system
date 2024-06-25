package edu.icet.clothify.dao.custom.category.impl;

import edu.icet.clothify.dao.custom.category.CategoryRepository;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.util.CategoryType;
import edu.icet.clothify.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;

@Slf4j
public class CategoryRepositoryImpl implements CategoryRepository {

    @Override
    public CategoryEntity findByType(CategoryType type) {
        CategoryEntity categoryType = null;
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            categoryType = session.createQuery("from category where categoryType = :categoryType", CategoryEntity.class)
                    .setParameter("categoryType", type.toString())
                    .getSingleResult();
            session.getTransaction().commit();
            return categoryType;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<CategoryEntity> findAll() {

        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<CategoryEntity> categoryEntityList = session.createQuery(
                    "from category", CategoryEntity.class).list();
            session.getTransaction().commit();
            return categoryEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
