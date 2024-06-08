package edu.icet.clothifystore.bo.custom.item;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Item;
import edu.icet.clothifystore.util.CategoryType;

public interface ItemBoService extends Service<Item> {
    <T extends Enum<CategoryType>> String generateId(T categoryType);
}
