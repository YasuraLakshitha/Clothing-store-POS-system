package edu.icet.clothifystore.bo.custom.item.impl;

import edu.icet.clothifystore.bo.custom.item.ItemBoService;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.item.ItemRepository;
import edu.icet.clothifystore.entity.ItemEntity;
import edu.icet.clothifystore.model.Item;
import edu.icet.clothifystore.model.Order;
import edu.icet.clothifystore.model.Product;
import edu.icet.clothifystore.model.SupplierItem;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.icet.clothifystore.util.product_type.KidsProductTypes.*;
import static edu.icet.clothifystore.util.product_type.MensProductTypes.*;
import static edu.icet.clothifystore.util.product_type.WomenProductTypes.*;

public class BoItemServiceImpl implements ItemBoService {

    private final ItemRepository repository = new DaoFactory().getDaoImpl(DaoType.ITEM);

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<Item> getAllItemsByProductType(String type) {
        List<ItemEntity> itemEntityList = repository.getByProductType(type);
        List<Item> itemList = new ArrayList<>();
        for (ItemEntity itemEntity : itemEntityList) {
            Set<SupplierItem> supplierItemSet = new HashSet<>();
            itemEntity.getSupplieritemEntitySet().forEach(supplierItemEntity -> {
                supplierItemSet.add(modelMapper.map(supplierItemEntity, SupplierItem.class));
            });
            Item item = modelMapper.map(itemEntity, Item.class);
            item.setSupplierItemSet(supplierItemSet);
            item.setProduct(modelMapper.map(itemEntity.getProductEntity(), Product.class));
            item.setOrder(modelMapper.map(itemEntity.getOrderEntity(), Order.class));
            itemList.add(item);
        }
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
    public Boolean save(Item object) {

        //TODO: save item
        return null;
    }

    @Override
    public Boolean update(Item object) {
        //TODO: update item
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: delete item
        return null;
    }

    @Override
    public Item findById(String id) {
        //TODO: find item by id
        return null;
    }
}
