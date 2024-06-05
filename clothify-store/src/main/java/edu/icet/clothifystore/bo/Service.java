package edu.icet.clothifystore.bo;

public interface Service<T> {
    Boolean save(T object);
    Boolean update(String id);
    Boolean delete(String id);
    T findById(String id);
}
