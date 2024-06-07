package edu.icet.clothifystore.dao.custom.product;

import edu.icet.clothifystore.dao.Crud;
import edu.icet.clothifystore.entity.ProductEntity;

import java.util.List;

public interface ProductRepository extends Crud<ProductEntity, String> {

    List<ProductEntity> findAll();
}
