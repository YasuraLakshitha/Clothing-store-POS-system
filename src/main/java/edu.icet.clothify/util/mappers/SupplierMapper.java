package edu.icet.clothify.util.mappers;

import edu.icet.clothify.entity.SupplierEntity;
import edu.icet.clothify.model.Supplier;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;


public class SupplierMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public SupplierMapper() {
        supplierMappingConfig();
    }

    private void supplierMappingConfig() {
        TypeMap<Supplier, SupplierEntity> mapSupplierEntity = modelMapper.createTypeMap(Supplier.class, SupplierEntity.class);
        mapSupplierEntity.addMappings(mapping -> {
            mapping.map(Supplier::getId, SupplierEntity::setId);
            mapping.map(Supplier::getStaffId, SupplierEntity::setStaffId);
            mapping.map(Supplier::getSupplierName, SupplierEntity::setSupplierName);
            mapping.map(Supplier::getSupplierEmail, SupplierEntity::setSupplierEmail);
            mapping.map(Supplier::getSupplierPassword, SupplierEntity::setSupplierPassword);
            mapping.map(Supplier::getCompanyName, SupplierEntity::setCompanyName);
            mapping.map(Supplier::getItemSet, SupplierEntity::setItemEntitySet);
            mapping.map(Supplier::getSupplierItemRecord, SupplierEntity::setSupplierItemRecord);
        });

        TypeMap<SupplierEntity, Supplier> mapEntityToSupplierDto = modelMapper.createTypeMap(SupplierEntity.class, Supplier.class);
        mapEntityToSupplierDto.addMappings(mapping -> {
            mapping.map(SupplierEntity::getId, Supplier::setId);
            mapping.map(SupplierEntity::getStaffId, Supplier::setStaffId);
            mapping.map(SupplierEntity::getSupplierName, Supplier::setSupplierName);
            mapping.map(SupplierEntity::getSupplierEmail, Supplier::setSupplierEmail);
            mapping.map(SupplierEntity::getSupplierPassword, Supplier::setSupplierPassword);
            mapping.map(SupplierEntity::getCompanyName, Supplier::setCompanyName);
            mapping.map(SupplierEntity::getItemEntitySet, Supplier::setItemSet);
            mapping.map(SupplierEntity::getSupplierItemRecord, Supplier::setSupplierItemRecord);
        });
    }


    public Supplier convert(SupplierEntity supplierEntity) {
        return new ModelMapper().map(supplierEntity, Supplier.class);
    }

    public SupplierEntity convert(Supplier supplier) {
        return new ModelMapper().map(supplier, SupplierEntity.class);
    }
}
