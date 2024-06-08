package edu.icet.clothifystore.bo.custom.category;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Category;
import edu.icet.clothifystore.util.BoType;

public interface CategoryService extends Service<Category> {

    Category findByType(BoType type);
}
