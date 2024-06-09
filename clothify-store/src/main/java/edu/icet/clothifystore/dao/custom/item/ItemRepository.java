package edu.icet.clothifystore.dao.custom.item;

import edu.icet.clothifystore.dao.Crud;
import edu.icet.clothifystore.entity.ItemEntity;

import java.util.List;

public interface ItemRepository extends Crud<ItemEntity, String> {

    List<ItemEntity> findAll();

    List<ItemEntity> getByProductType(String type);
}
