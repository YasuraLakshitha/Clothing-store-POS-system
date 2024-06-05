package edu.icet.clothifystore.dao;

public interface Crud<T,V>{
    Boolean save(T t);
    T findById(V id);
    Boolean delete(V id);
    Boolean update(V id);
}
