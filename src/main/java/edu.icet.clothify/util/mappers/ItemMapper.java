package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Product;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

public class ItemMapper {
    public static Converter<Product, ProductEntity> productToProductEntityConverter = context -> {
        if (context.getSource() == null) {
            return null;
        }
        Product product = context.getSource();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setProductName(product.getProductName());
        productEntity.setProductDescription(product.getProductDescription());
        productEntity.setProductPrice(product.getProductPrice());
        productEntity.setProductQuantity(product.getProductQuantity());
        return productEntity;
    };
    public static Converter<ProductEntity, Product> productEntityToProductConverter = context -> {
        if (context.getSource() == null) {
            return null;
        }
        ProductEntity productEntity = context.getSource();
        Product product = new Product();
        product.setId(productEntity.getId());
        product.setProductName(productEntity.getProductName());
        product.setProductDescription(productEntity.getProductDescription());
        product.setProductPrice(productEntity.getProductPrice());
        product.setProductQuantity(productEntity.getProductQuantity());
        return product;
    };
    private final ModelMapper modelMapper;

    public ItemMapper() {
        modelMapper = new ModelMapper();
        itemConfig();
        modelMapper.addConverter(productToProductEntityConverter);
        modelMapper.addConverter(productEntityToProductConverter);
    }

    private void itemConfig() {
        TypeMap<Item, ItemEntity> entityMap = modelMapper.createTypeMap(Item.class, ItemEntity.class);
        entityMap.addMappings(mapping -> {
            mapping.map(Item::getId, ItemEntity::setId);
            mapping.map(Item::getItemName, ItemEntity::setItemName);
            mapping.map(Item::getItemSize, ItemEntity::setItemSize);
            mapping.map(Item::getProduct, ItemEntity::setProductEntity);
        });

        TypeMap<ItemEntity, Item> dtoMap = modelMapper.createTypeMap(ItemEntity.class, Item.class);
        dtoMap.addMappings(mapping -> {
            mapping.map(ItemEntity::getId, Item::setId);
            mapping.map(ItemEntity::getItemName, Item::setItemName);
            mapping.map(ItemEntity::getItemSize, Item::setItemSize);
        });
    }

    public Item convert(ItemEntity itemEntity) {
        return modelMapper.map(itemEntity, Item.class);
    }

    public ItemEntity convert(Item item) {
        return modelMapper.map(item, ItemEntity.class);
    }
}
