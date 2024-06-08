package edu.icet.clothifystore.bo.custom.category.impl;

import edu.icet.clothifystore.bo.custom.category.CategoryService;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.category.CategoryRepository;
import edu.icet.clothifystore.model.Category;
import edu.icet.clothifystore.util.BoType;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

public class BoCategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository = new DaoFactory().getDaoImpl(DaoType.CATEGORY);

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean save(Category object) {
        //TODO: save category
        return null;
    }

    @Override
    public Boolean update(Category object) {
        //TODO: update category
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: delete category
        return null;
    }

    @Override
    public Category findById(String id) {
        //TODO: find category by id
        return null;
    }

    @Override
    public Category findByType(BoType type) {
        return modelMapper.map(categoryRepository.findByType(type), Category.class);
    }
}
