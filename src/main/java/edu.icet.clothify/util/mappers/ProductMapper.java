package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.ItemEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class ProductMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public ProductMapper() {
        productMapperConfig();
    }

    private void productMapperConfig() {
        modelMapper.createTypeMap(Product.class, ProductEntity.class)
                .addMappings(mapper -> {
                    mapper.map(Product::getId, ProductEntity::setId);
                    mapper.map(Product::getProductName, ProductEntity::setProductName);
                    mapper.map(Product::getProductDescription, ProductEntity::setProductDescription);
                    mapper.map(Product::getProductQuantity, ProductEntity::setProductQuantity);
                    mapper.map(Product::getProductPrice, ProductEntity::setProductPrice);
                    mapper.map(src -> mapItemsToItemEntities(src.getItemSet()), ProductEntity::setItemEntitySet);
                });

        modelMapper.createTypeMap(ProductEntity.class, Product.class);
        TypeMap<ProductEntity, Product> typeMap = modelMapper.getTypeMap(ProductEntity.class, Product.class);
        if (typeMap == null) {
            typeMap = modelMapper.createTypeMap(ProductEntity.class, Product.class);
        }

        typeMap.addMappings(mapper -> {
            mapper.map(ProductEntity::getId, Product::setId);
            mapper.map(ProductEntity::getProductName, Product::setProductName);
            mapper.map(ProductEntity::getProductDescription, Product::setProductDescription);
            mapper.map(ProductEntity::getProductQuantity, Product::setProductQuantity);
            mapper.map(ProductEntity::getProductPrice, Product::setProductPrice);
            mapper.map(source -> mapItemEntitiesToItems(source.getItemEntitySet()), Product::setItemSet);
        });
    }

    private Set<ItemEntity> mapItemsToItemEntities(Set<Item> itemSet) {
        if (itemSet == null) return new HashSet<>();
        return itemSet.stream().map(item -> modelMapper.map(item, ItemEntity.class)).collect(Collectors.toSet());
    }


    private Set<Item> mapItemEntitiesToItems(Set<ItemEntity> itemEntities) {
        if (itemEntities == null) return new HashSet<>();
        return itemEntities.stream().map(itemEntity -> modelMapper.map(itemEntity, Item.class)).collect(Collectors.toSet());
    }

    public Product convert(ProductEntity productEntity) {
        return modelMapper.map(productEntity, Product.class);
    }

    public ProductEntity convert(Product product) {
        return modelMapper.map(product, ProductEntity.class);
    }

}

