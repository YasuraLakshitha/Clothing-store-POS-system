package edu.icet.clothify.dao.custom.category;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.util.CategoryType;

import java.util.List;

public interface CategoryRepository extends Crud<CategoryEntity, String> {
    CategoryEntity findByType(CategoryType type);

    List<CategoryEntity> findAll();
}
