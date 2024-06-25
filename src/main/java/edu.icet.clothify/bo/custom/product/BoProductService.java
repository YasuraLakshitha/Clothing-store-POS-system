package edu.icet.clothify.bo.custom.product;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Product;
import edu.icet.clothify.util.CategoryType;

import java.util.List;

public interface BoProductService extends Service<Product> {

    List<Product> getByCategoryType(CategoryType value);

    List<Product> findAll();

    Product findByType(String value);

    List<Product> findByItem(Item item);
}
