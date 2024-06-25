package edu.icet.clothify.dao.custom.item;

import edu.icet.clothify.dao.Crud;
import edu.icet.clothify.entity.ItemEntity;

import java.util.List;

public interface ItemRepository extends Crud<ItemEntity, String> {

    List<ItemEntity> findAll();

    List<ItemEntity> getByProductType(String type);

    ItemEntity find(ItemEntity itemEntity);
}
