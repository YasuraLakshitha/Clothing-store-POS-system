package edu.icet.clothifystore.dao.custom.category;

import edu.icet.clothifystore.dao.Crud;
import edu.icet.clothifystore.entity.CategoryEntity;
import edu.icet.clothifystore.util.BoType;

public interface CategoryRepository extends Crud<CategoryEntity, String> {
    CategoryEntity findByType(BoType type);
}
