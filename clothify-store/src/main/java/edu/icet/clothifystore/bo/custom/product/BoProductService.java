package edu.icet.clothifystore.bo.custom.product;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Product;
import edu.icet.clothifystore.util.CategoryType;

import java.util.List;

public interface BoProductService extends Service<Product> {

    List<Product> getByCategoryType(Enum<CategoryType> value);

    Product findByType(String value);
}
