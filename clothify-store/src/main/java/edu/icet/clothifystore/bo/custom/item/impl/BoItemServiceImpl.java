package edu.icet.clothifystore.bo.custom.item.impl;

import edu.icet.clothifystore.bo.custom.item.ItemBoService;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.item.ItemRepository;
import edu.icet.clothifystore.entity.ItemEntity;
import edu.icet.clothifystore.model.Item;
import edu.icet.clothifystore.util.CategoryType;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static edu.icet.clothifystore.util.CategoryType.*;


public class BoItemServiceImpl implements ItemBoService {

    private final ItemRepository itemRepository = new DaoFactory().getDaoImpl(DaoType.ITEM);

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public <T extends Enum<CategoryType>> String generateId(T type) {
        List<ItemEntity> itemEntityList = itemRepository.findAll();
        List<String> ids = new ArrayList<>();
        itemEntityList.forEach(itemEntity -> {
            if (itemEntity.getProductEntity().getCategoryEntity().equals(type.toString()))
                ids.add(itemEntity.getId());
        });

        String idPattern = null;

        if (type.toString().equals(MEN.toString())) {
            idPattern = "(PR-MC)([0-9]{3})";
            if (ids.isEmpty()) return "PR-MC001";
        } else if (type.toString().equals(WOMEN.toString())) {
            idPattern = "(PR-WC)([0-9]{3})";
            if (ids.isEmpty()) return "PR-WC001";
        } else if (type.toString().equals(KIDS.toString())) {
            idPattern = "(PR-KC)([0-9]{3})";
            if (ids.isEmpty()) return "PR-KC001";
        } else {
            idPattern = "(PR-FC)([0-9]{3})";
            if (ids.isEmpty()) return "PR-FC001";
        }

        Pattern pattern = Pattern.compile(idPattern);
        Matcher matcher = pattern.matcher("PR-MC001");
        if (matcher.find())
            return String.format("%s%03d", matcher.group(1), Integer.parseInt(matcher.group(2)) + ids.size());
        return null;
    }

    @Override
    public Boolean save(Item object) {
        //TODO: map object to entity
        //TODO: map category to entity
        return null;
    }

    @Override
    public Boolean update(Item object) {
        //TODO: retrieve by id
        //TODO: map object to entity
        //TODO: merge
        return null;
    }

    @Override
    public Boolean delete(String id) {
        //TODO: delete product
        return null;
    }

    @Override
    public Item findById(String id) {
        //TODO: retrieve product
        //TODO: map entity values to product and return
        return null;
    }
}
