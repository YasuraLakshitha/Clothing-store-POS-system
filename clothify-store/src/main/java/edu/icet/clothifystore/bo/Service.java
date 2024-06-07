package edu.icet.clothifystore.bo;

public interface Service<T> extends SupperBo {
    Boolean save(T object);
    Boolean update(T object);
    Boolean delete(String id);
    T findById(String id);
}
