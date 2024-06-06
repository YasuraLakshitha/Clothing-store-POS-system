package edu.icet.clothifystore.dao.custom.admin.impl;

import edu.icet.clothifystore.dao.custom.admin.AdminRepository;
import edu.icet.clothifystore.entity.AdminEntity;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class AdminRepositoryImpl implements AdminRepository {

    private final Session session = HibernateUtil.getSession();
    @Override
    public Boolean save(AdminEntity adminEntity) {
        session.beginTransaction();
        session.persist(adminEntity);
        session.getTransaction().commit();
        session.clear();
        return true;
    }

    @Override
    public AdminEntity findById(String id) {
        session.beginTransaction();
        AdminEntity adminEntity = session.get(AdminEntity.class, id);
        session.getTransaction().commit();
        return adminEntity;
    }

    @Override
    public Boolean delete(String id) {
        session.beginTransaction();
        session.remove(session.get(AdminEntity.class, id));
        session.getTransaction().commit();
        return !session.contains(session.get(AdminEntity.class, id));
    }

    @Override
    public Boolean update(AdminEntity object) {
        session.beginTransaction();
        session.merge(object);
        session.getTransaction().commit();
        return true;
    }


    @Override
    public List<AdminEntity> findAllAdmins() {
        session.beginTransaction();
        List<AdminEntity> adminEntityList = session.createQuery("from admin", AdminEntity.class).list();
        session.getTransaction().commit();
        return adminEntityList;
    }

    @Override
    public AdminEntity findByEmailAndPassword(String email, String password) {
        session.beginTransaction();
        AdminEntity adminEntity = session.createQuery("from admin where adminPassword = :password and adminEmail = : email", AdminEntity.class)
                .setParameter("password", password)
                .setParameter("email", email)
                .getSingleResult();
        session.getTransaction().commit();
        return adminEntity;
    }

    @Override
    public AdminEntity findAdminByEmail(String email) {
        session.beginTransaction();
        AdminEntity adminEntity = session.createQuery("from admin where  adminEmail = :email",AdminEntity.class)
                .setParameter("email",email)
                .getSingleResult();
        session.getTransaction().commit();
        return adminEntity;
    }
}
