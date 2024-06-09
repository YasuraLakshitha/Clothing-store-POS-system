package edu.icet.clothifystore.bo.custom.item;

import edu.icet.clothifystore.bo.Service;
import edu.icet.clothifystore.model.Item;

import java.util.List;

public interface ItemBoService extends Service<Item> {

    List<Item> getAllItemsByProductType(String productType);

    String setItemId(String productType);
}
