package edu.icet.clothify.bo.custom.category.impl;

import edu.icet.clothify.bo.custom.category.BoCategoryService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.category.CategoryRepository;
import edu.icet.clothify.entity.CategoryEntity;
import edu.icet.clothify.model.Category;
import edu.icet.clothify.util.CategoryType;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.CategoryMapper;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

public class BoCategoryServiceImpl implements BoCategoryService {

    private final CategoryRepository categoryRepository = new DaoFactory().getDaoImpl(DaoType.CATEGORY);

    private final CategoryMapper categoryMapper = new CategoryMapper();

    @Override
    public Boolean save(Category object) {
        return categoryRepository.save(categoryMapper.convert(object));
    }

    @Override
    public Boolean update(Category object) {
        return categoryRepository.update(categoryMapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return categoryRepository.delete(id);
    }

    @Override
    public Category findById(String id) {
        return categoryMapper.convert(categoryRepository.findById(id));
    }

    @Override
    public Category findByType(CategoryType type) {
        return categoryMapper.convert(categoryRepository.findByType(type));
    }

    @Override
    public List<Category> findAll() {
        final List<CategoryEntity> categoryEntityList = categoryRepository.findAll();
        List<Category> categoryList = new ArrayList<>();
        for (CategoryEntity categoryEntity : categoryEntityList) {
            categoryList.add(categoryMapper.convert(categoryEntity));
        }
        return categoryList;
    }
}
