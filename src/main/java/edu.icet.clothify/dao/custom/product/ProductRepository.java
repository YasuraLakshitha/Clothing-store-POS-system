package edu.icet.clothify.dao.custom.product;


import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.util.CategoryType;

import java.util.List;

public interface ProductRepository extends Crud<ProductEntity, String> {
    List<ProductEntity> findByCategoryType(CategoryType value);

    ProductEntity findByType(String type);

    List<ProductEntity> findAll();

    List<ProductEntity> findByItem(ItemEntity itemEntity);
}
