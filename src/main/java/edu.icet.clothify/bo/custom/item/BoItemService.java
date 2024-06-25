package edu.icet.clothify.bo.custom.item;

import edu.icet.clothify.bo.Service;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Product;

import java.util.List;

public interface BoItemService extends Service<Item> {

    List<Item> getAllItemsByProductType(String productType);

    String setItemId(String productType);
}
