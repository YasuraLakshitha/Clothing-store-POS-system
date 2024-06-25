package edu.icet.clothify.dao;

public interface Crud<T, V> extends SupperDao {
    Boolean save(T t);

    T findById(V id);

    Boolean delete(V id);

    Boolean update(T object);

    //void setCommit(Boolean commit);
}
