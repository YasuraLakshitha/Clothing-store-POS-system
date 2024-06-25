package edu.icet.clothify.bo.custom.category;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Category;
import edu.icet.clothify.util.CategoryType;

import java.util.List;

public interface BoCategoryService extends Service<Category> {
    Category findByType(CategoryType type);

    List<Category> findAll();
}

