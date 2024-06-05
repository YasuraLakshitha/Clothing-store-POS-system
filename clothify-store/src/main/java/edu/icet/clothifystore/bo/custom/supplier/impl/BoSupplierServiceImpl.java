package edu.icet.clothifystore.bo.custom.supplier.impl;

import edu.icet.clothifystore.bo.custom.supplier.BoSupplierService;
import edu.icet.clothifystore.dao.DaoFactory;
import edu.icet.clothifystore.dao.custom.supplier.SupplierRepository;
import edu.icet.clothifystore.entity.SupplierEntity;
import edu.icet.clothifystore.model.Supplier;
import edu.icet.clothifystore.util.DaoType;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BoSupplierServiceImpl implements BoSupplierService {

    private final SupplierRepository repository = new DaoFactory().getDaoImpl(DaoType.SUPPLIER);

    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public Boolean save(Supplier object) {
       return repository.save(modelMapper.map(object, SupplierEntity.class));
    }

    @Override
    public Boolean update(String id) {
        return null;
    }

    @Override
    public Boolean delete(String id) {
        return null;
    }

    @Override
    public Supplier findById(String id) {
        return null;
    }

    @Override
    public List<Supplier> findAll(){
        List<SupplierEntity> supplierEntities = repository.getAllSuppliers();
        if (supplierEntities.isEmpty()) return null;
        List<Supplier> supplierList = new ArrayList<>();
        for (SupplierEntity supplierEntity : supplierEntities) {
            supplierList.add(modelMapper.map(supplierEntity,Supplier.class));
        }
        return supplierList;
    }

    @Override
    public String generateSupplierId() {
        List<SupplierEntity> supplierList = repository.getAllSuppliers();
        System.out.println(supplierList.size());
        if (supplierList.isEmpty()) return "SUP001";
        Pattern pattern = Pattern.compile("SUP([0-9]{3})");
        Matcher matcher = pattern.matcher(supplierList.get(supplierList.size()-1).getId());
        Integer nextSupplierId = null;
        while (matcher.find()) {
             nextSupplierId = Integer.parseInt(matcher.group(1));
        }
        if (nextSupplierId!=null) return String.format("SUP%03d", ++nextSupplierId);
        return null;
    }
}
