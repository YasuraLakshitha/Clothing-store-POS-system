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
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null; //TODO Delete employee
    }

    @Override
    public Boolean update(String id) {
        return null;
    }

    @Override
    public List<EmployeeEntity> findAll() {
        session.beginTransaction();
        List<EmployeeEntity> employeeEntityList = session.createQuery(
                "from employee ", EmployeeEntity.class).list();
        session.getTransaction().commit();
        return employeeEntityList;
    }
}
