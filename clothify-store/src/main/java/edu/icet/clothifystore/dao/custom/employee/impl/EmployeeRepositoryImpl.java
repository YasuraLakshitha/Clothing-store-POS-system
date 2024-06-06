package edu.icet.clothifystore.dao.custom.employee.impl;

import edu.icet.clothifystore.dao.custom.employee.EmployeeRepository;
import edu.icet.clothifystore.entity.EmployeeEntity;
import edu.icet.clothifystore.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EmployeeRepositoryImpl implements EmployeeRepository {
    private final Session session = HibernateUtil.getSession();
    @Override
    public Boolean save(EmployeeEntity employeeEntity) {
        session.beginTransaction();
        session.persist(employeeEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public EmployeeEntity findById(String id) {
        session.beginTransaction();
        EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, id);
        session.getTransaction().commit();
        return employeeEntity;
    }

    @Override
    public Boolean delete(String id) {
        session.beginTransaction();
        session.remove(session.get(EmployeeEntity.class, id));
        session.getTransaction().commit();
        return !session.contains(session.get(EmployeeEntity.class, id));
    }

    @Override
    public Boolean update(EmployeeEntity object) {
        session.beginTransaction();
        session.merge(object);
        return true;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        session.beginTransaction();
        List<EmployeeEntity> employeeEntityList = session.createQuery(
                "from employee ", EmployeeEntity.class).list();
        session.getTransaction().commit();
        return employeeEntityList;
    }

    @Override
    public EmployeeEntity findByEmailAndPassword(String email, String password) {
        session.beginTransaction();
        EmployeeEntity employeeEntity = session.createQuery(
                        "from employee where employeeEmail = :email and employeePassword = :password", EmployeeEntity.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getSingleResult();
        session.getTransaction().commit();
        return employeeEntity;
    }

    @Override
    public EmployeeEntity findByEmail(String email) {
        session.beginTransaction();
        EmployeeEntity employeeEntity = session.createQuery("from employee where employeeEmail = :email", EmployeeEntity.class)
                .setParameter("email", email)
                .getSingleResult();
        session.getTransaction().commit();
        return employeeEntity;
    }
}
