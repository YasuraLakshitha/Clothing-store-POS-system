package edu.icet.clothify.dao.custom.employee.impl;

import edu.icet.clothify.dao.custom.employee.EmployeeRepository;
import edu.icet.clothify.entity.EmployeeEntity;
import edu.icet.clothify.util.HibernateUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;

import java.util.List;

@Slf4j
public class EmployeeRepositoryImpl implements EmployeeRepository {

    private final Session session = HibernateUtil.getSession();

    @Override
    public Boolean save(EmployeeEntity employeeEntity) {

        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.persist(employeeEntity);
            session.getTransaction().commit();
            return session.contains(employeeEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public EmployeeEntity findById(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            EmployeeEntity employeeEntity = session.get(EmployeeEntity.class, id);
            session.getTransaction().commit();
            log.error(employeeEntity.toString());
            return employeeEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean delete(String id) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            session.remove(session.get(EmployeeEntity.class, id));
            session.getTransaction().commit();
            return !session.contains(session.get(EmployeeEntity.class, id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean update(EmployeeEntity object) {
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

    @Override
    public List<EmployeeEntity> findAll() {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            List<EmployeeEntity> employeeEntityList = session.createQuery(
                    "from employee ", EmployeeEntity.class).list();
            session.getTransaction().commit();
            return employeeEntityList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EmployeeEntity findByEmailAndPassword(String email, String password) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            EmployeeEntity employeeEntity = session.createQuery(
                            "from employee where employeeEmail = :email and employeePassword = :password", EmployeeEntity.class)
                    .setParameter("email", email).setParameter("password", password)
                    .getSingleResult();
            session.getTransaction().commit();
            return employeeEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public EmployeeEntity findByEmail(String email) {
        try (Session session = HibernateUtil.getSession()) {
            session.getTransaction().begin();
            EmployeeEntity employeeEntity = session.createQuery(
                            "from employee where employeeEmail = :email", EmployeeEntity.class)
                    .setParameter("email", email).getSingleResult();
            session.getTransaction().commit();
            return employeeEntity;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
