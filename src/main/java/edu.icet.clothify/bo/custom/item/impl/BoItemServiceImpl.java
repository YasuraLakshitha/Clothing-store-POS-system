package edu.icet.clothify.bo.custom.item.impl;

import edu.icet.clothify.bo.custom.item.BoItemService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.item.ItemRepository;
import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.OrderEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Product;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.ItemMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.icet.clothify.util.product_type.KidsProductTypes.*;
import static edu.icet.clothify.util.product_type.MensProductTypes.*;
import static edu.icet.clothify.util.product_type.WomenProductTypes.*;

public class BoItemServiceImpl implements BoItemService {

    private final ItemRepository repository = new DaoFactory().getDaoImpl(DaoType.ITEM);

    private final ItemMapper itemMapper = new ItemMapper();

    @Override
    public List<Item> getAllItemsByProductType(String type) {
        List<ItemEntity> itemEntityList = repository.getByProductType(type);
        List<Item> itemList = new ArrayList<>();
        itemEntityList.forEach(itemEntity -> {
            itemList.add(itemMapper.convert(itemEntity));
        });
        return itemList;
    }

    @Override
    public String setItemId(String productType) {
        List<Item> itemList = getAllItemsByProductType(productType);
        String idPattern = null;
        System.out.println(productType);
        if (productType.equals(T_SHIRT_MEN.toString())) {
            idPattern = "(PMT)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PMT001";
            }
        } else if (productType.equals(JEANS_MEN.toString())) {
            idPattern = "(PMRJE)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PMRJE001";
            }
        } else if (productType.equals(JACKET_MEN.toString())) {
            idPattern = "(PMJ)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PMJ001";
            }
        } else if (productType.equals(FOOTWEAR_MEN.toString())) {
            idPattern = "(PMTF)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PMTF001";
            }
        } else if (productType.equals(DRESS_WOMEN.toString())) {
            idPattern = "(PWD)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PWD001";
            }
        } else if (productType.equals(BLOUSE_WOMEN.toString())) {
            idPattern = "(PWB)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PWB001";
            }
        } else if (productType.equals(SKIRT_WOMEN.toString())) {
            idPattern = "(PWS)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PWS001";
            }
        } else if (productType.equals(FOOTWEAR_WOMEN.toString())) {
            idPattern = "(PWF)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PWF001";
            }
        } else if (productType.equals(T_SHIRT_KIDS.toString())) {
            idPattern = "(PKT)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PKT001";
            }
        } else if (productType.equals(SHORTS_KIDS.toString())) {
            idPattern = "(PKS)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PKS001";
            }
        } else if (productType.equals(JACKET_KIDS.toString())) {
            idPattern = "(PKJ)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PKJ001";
            }
        } else if (productType.equals(FOOTWEAR_KIDS.toString())) {
            idPattern = "(PKF)([0-9]{3})";
            if (itemList.isEmpty()) {
                return "PKF001";
            }
        }
        Pattern pattern = Pattern.compile(idPattern);
        Matcher matcher = null;
        if (!itemList.isEmpty()) {
            matcher = pattern.matcher(itemList.get(itemList.size() - 1).getId());
            if (matcher.find()) {
                return String.format("%s%03d", matcher.group(1), Integer.parseInt(matcher.group(2) + 1));
            }
        }
        return null;
    }


    @Override
    public Boolean save(Item item) {
        return repository.save(itemMapper.convert(item));
    }

    @Override
    public Boolean update(Item object) {
        ItemEntity itemEntity = itemMapper.convert(object);
        if (object.getOrder() != null) {
            OrderEntity orderEntity = new OrderEntity();
            orderEntity.setId(object.getOrder().getId());
            itemEntity.setOrderEntity(orderEntity);
        }
        return repository.update(itemEntity);
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Item findById(String id) {
        return itemMapper.convert(repository.findById(id));
    }
}
