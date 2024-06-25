package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Category;
import edu.icet.clothify.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class CategoryMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public CategoryMapper() {
        mappingConfig();
    }

    private void mappingConfig() {
        TypeMap<Category, CategoryEntity> categoryMap = modelMapper.createTypeMap(Category.class, CategoryEntity.class);
        categoryMap.addMapping(Category::getId, CategoryEntity::setId);
        categoryMap.addMapping(Category::getCategoryType, CategoryEntity::setCategoryType);
        categoryMap.addMapping(Category::getCategoryDescription, CategoryEntity::setCategoryDescription);
        categoryMap.addMapping(source -> source.getProductSet() != null ? source.getProductSet().stream()
                        .map(product -> modelMapper.map(product, ProductEntity.class))
                        .collect(Collectors.toSet()) : null
                , CategoryEntity::setProductsEntitySet
        );

        TypeMap<CategoryEntity, Category> inverseCategoryMap = modelMapper.createTypeMap(CategoryEntity.class, Category.class);
        inverseCategoryMap.addMapping(CategoryEntity::getId, Category::setId);
        inverseCategoryMap.addMapping(CategoryEntity::getCategoryType, Category::setCategoryType);
        inverseCategoryMap.addMapping(CategoryEntity::getCategoryDescription, Category::setCategoryDescription);
        inverseCategoryMap.addMapping(source -> source.getProductsEntitySet() != null ? source.getProductsEntitySet().stream()
                        .map(productEntity -> modelMapper.map(productEntity.getId(), Product.class)).collect(Collectors.toSet()) : null
                , Category::setProductSet);
    }

    public Category convert(CategoryEntity categoryEntity) {
        if (categoryEntity == null) return null;
        return modelMapper.map(categoryEntity, Category.class);
    }

    public CategoryEntity convert(Category category) {
        return modelMapper.map(category, CategoryEntity.class);
    }
}
