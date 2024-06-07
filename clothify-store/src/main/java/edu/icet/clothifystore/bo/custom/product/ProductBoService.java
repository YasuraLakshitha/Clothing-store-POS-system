package edu.icet.clothifystore.bo.custom.product;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Product;
import edu.icet.clothifystore.util.CategoryType;

public interface ProductBoService extends Service<Product> {
    <T extends Enum<CategoryType>> String generateId(T categoryType);
}
