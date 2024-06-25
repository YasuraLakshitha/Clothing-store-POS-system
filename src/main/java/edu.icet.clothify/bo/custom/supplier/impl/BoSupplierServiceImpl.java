package edu.icet.clothify.bo.custom.supplier.impl;

import edu.icet.clothify.bo.custom.supplier.BoSupplierService;
import edu.icet.clothify.dao.DaoFactory;
import edu.icet.clothify.dao.custom.supplier.SupplierRepository;
import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.model.Supplier;
import edu.icet.clothify.util.DaoType;
import edu.icet.clothify.util.mappers.SupplierMapper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class BoSupplierServiceImpl implements BoSupplierService {

    private final SupplierRepository repository = new DaoFactory().getDaoImpl(DaoType.SUPPLIER);

    private final SupplierMapper mapper = new SupplierMapper();

    @Override
    public Boolean save(Supplier object) {
        return repository.save(mapper.convert(object));
    }

    @Override
    public Boolean update(Supplier object) {
        return repository.update(mapper.convert(object));
    }

    @Override
    public Boolean delete(String id) {
        return repository.delete(id);
    }

    @Override
    public Supplier findById(String id) {
        return mapper.convert(repository.findById(id));
    }

    @Override
    public List<Supplier> findAll() {
        return repository.getAllSuppliers().stream()
                .map(mapper::convert)
                .collect(Collectors.toList());
    }

    @Override
    public String generateSupplierId() {
        List<SupplierEntity> supplierList = repository.getAllSuppliers();
        if (supplierList.isEmpty()) return "SUP001";
        Pattern pattern = Pattern.compile("SUP([0-9]{3})");
        Matcher matcher = pattern.matcher(supplierList.get(supplierList.size() - 1).getId());
        Integer nextSupplierId = null;
        while (matcher.find()) {
            nextSupplierId = Integer.parseInt(matcher.group(1));
        }
        if (nextSupplierId != null) return String.format("SUP%03d", ++nextSupplierId);
        return null;
    }

    @Override
    public Supplier findSupplierByEmail(String email) {
        return mapper.convert(repository.findByEmail(email));
    }
}
