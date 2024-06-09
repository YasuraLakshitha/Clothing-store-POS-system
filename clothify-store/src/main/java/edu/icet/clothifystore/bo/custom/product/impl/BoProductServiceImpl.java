package edu.icet.clothifystore.bo.custom.product.impl;

import edu.icet.clothifystore.bo.custom.product.BoProductService;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.product.ProductRepository;
import edu.icet.clothifystore.entity.ProductEntity;
import edu.icet.clothifystore.model.Category;
import edu.icet.clothifystore.model.Item;
import edu.icet.clothifystore.model.Product;
import edu.icet.clothifystore.util.CategoryType;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoProductServiceImpl implements BoProductService {

    private final ModelMapper modelMapper = new ModelMapper();

    private final ProductRepository repository = new DaoFactory().getDaoImpl(DaoType.PRODUCT);


    @Override
    public Boolean save(Product object) {
        //TODO: save
        return null;
    }

    @Override
    public Boolean update(Product object) {
        //TODO: update
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: delete
        return null;
    }

    @Override
    public Product findById(String id) {
        //TODO: find by ID
        return null;
    }

    @Override
    public List<Product> getByCategoryType(Enum<CategoryType> value) {
        List<ProductEntity> productEntityList = repository.findByCategoryType(value);
        List<Product> productList = new ArrayList<>();
        productEntityList.forEach(productEntity -> {
            //TODO: map inside the item entity --> CONS:unnecessary complexity / PROS: flexible
            Set<Item> itemSet = new HashSet<>();
            productEntity.getItemEntitySet().forEach(itemEntity -> {
                itemSet.add(modelMapper.map(itemEntity, Item.class));
            });
            Category category = modelMapper.map(productEntity.getCategoryEntity(), Category.class);
            Product product = modelMapper.map(productEntity, Product.class);

            product.setCategory(category);
            product.setItemSet(itemSet);
            productList.add(product);
        });
        return productList;
    }

    @Override
    public Product findByType(String type) {
        ProductEntity productEntity = repository.findByType(type);
        Set<Item> itemSet = new HashSet<>();
        productEntity.getItemEntitySet().forEach(itemEntity -> {
            itemSet.add(modelMapper.map(itemEntity, Item.class));
        });
        Category category = modelMapper.map(productEntity.getCategoryEntity(), Category.class);
        Product product = modelMapper.map(productEntity, Product.class);
        product.setCategory(category);
        product.setItemSet(itemSet);
        return product;
    }
}
