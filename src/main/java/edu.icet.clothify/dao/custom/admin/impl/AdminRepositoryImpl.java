package edu.icet.clothify.dao.custom.admin.impl;

import edu.icet.clothify.dao.custom.admin.AdminRepository;
import edu.icet.clothify.entity.AdminEntity;
import edu.icet.clothify.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {


    @Override
    public Boolean save(AdminEntity adminEntity) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.persist(adminEntity);
            session.getTransaction().commit();
            return session.contains(adminEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public AdminEntity findById(String id) {
        AdminEntity adminEntity;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            adminEntity = session.get(AdminEntity.class, id);
            session.getTransaction().commit();
            return adminEntity;
        }
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.remove(session.get(AdminEntity.class, id));
            session.getTransaction().commit();
            return !session.contains(session.get(AdminEntity.class, id));
        }
    }

    @Override
    public Boolean update(AdminEntity object) {
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            session.merge(object);
            session.getTransaction().commit();
            return session.contains(object);
        }
    }


    @Override
    public List<AdminEntity> findAllAdmins() {
        List<AdminEntity> adminEntityList;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            adminEntityList = session.createQuery("from admin", AdminEntity.class).list();
            session.getTransaction().commit();
        }
        return adminEntityList;
    }

    @Override
    public AdminEntity findByEmailAndPassword(String email, String password) {
        AdminEntity adminEntity;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            adminEntity = session.createQuery(
                            "from admin where adminPassword = :password and adminEmail = :email", AdminEntity.class)
                    .setParameter("password", password)
                    .setParameter("email", email)
                    .getSingleResult();
            session.getTransaction().commit();
        }
        return adminEntity;
    }

    @Override
    public AdminEntity findAdminByEmail(String email) {
        AdminEntity adminEntity;
        try (Session session = HibernateUtil.getSession()) {
            session.beginTransaction();
            adminEntity = session.createQuery(
                            "from admin where adminEmail = :email", AdminEntity.class)
                    .setParameter("email", email)
                    .getSingleResult();
            session.getTransaction().commit();
        }
        return adminEntity;
    }
}
