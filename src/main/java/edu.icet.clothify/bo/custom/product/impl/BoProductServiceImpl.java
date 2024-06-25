package edu.icet.clothify.bo.custom.product.impl;

import edu.icet.clothify.bo.custom.product.BoProductService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.product.ProductRepository;
import edu.icet.clothify.entity.ProductEntity;
import edu.icet.clothify.model.Item;
import edu.icet.clothify.model.Product;
import edu.icet.clothify.util.CategoryType;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.ItemMapper;
import edu.icet.clothify.util.mappers.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class BoProductServiceImpl implements BoProductService {

    private final ProductRepository repository = new DaoFactory().getDaoImpl(DaoType.PRODUCT);

    private final ProductMapper mapper = new ProductMapper();

    @Override
    public Boolean save(Product object) {
        return repository.save(mapper.convert(object));
    }

    @Override
    public Boolean update(Product product) {
        return repository.update(mapper.convert(product));
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Product findById(String id) {
        return mapper.convert(repository.findById(id));
    }

    @Override
    public List<Product> getByCategoryType(CategoryType value) {
        List<ProductEntity> productEntityList = repository.findByCategoryType(value);
        List<Product> productList = new ArrayList<>();
        for (ProductEntity productEntity : productEntityList)
            productList.add(mapper.convert(productEntity));
        return productList;
    }

    @Override
    public List<Product> findAll() {
        final List<ProductEntity> productEntityList = repository.findAll();
        final List<Product> productList = new ArrayList<>();
        productEntityList.forEach(productEntity -> {
            productList.add(mapper.convert(productEntity));
        });
        return productList;
    }

    @Override
    public Product findByType(String type) {
        return mapper.convert(repository.findByType(type));
    }

    @Override
    public List<Product> findByItem(Item item) {
        return repository.findByItem(new ItemMapper().convert(item)).stream()
                .map(mapper::convert).collect(Collectors.toList());
    }
}
