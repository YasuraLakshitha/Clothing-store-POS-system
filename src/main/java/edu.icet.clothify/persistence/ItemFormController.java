package edu.icet.clothify.persistence;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.category.BoCategoryService;
import edu.icet.clothify.bo.custom.item.BoItemService;
import edu.icet.clothify.bo.custom.product.BoProductService;
import edu.icet.clothify.bo.custom.supplier.BoSupplierService;
import edu.icet.clothify.model.*;
import edu.icet.clothify.util.BoType;
import edu.icet.clothify.util.CategoryType;
import edu.icet.clothify.util.itemsizes.ItemSizes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.slf4j.Logger;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class ItemFormController implements Initializable {

    private final BoItemService itemService = new BoFactory().createBoImpl(BoType.ITEM);

    private final BoCategoryService categoryService = new BoFactory().createBoImpl(BoType.CATEGORY);

    private final BoProductService productService = new BoFactory().createBoImpl(BoType.PRODUCT);

    private final BoSupplierService supplierService = new BoFactory().createBoImpl(BoType.SUPPLIER);

    @FXML
    public Button btnSearch;

    @FXML
    public TextField txtItemPrice;

    @FXML
    public TextField txtItemQty;

    @FXML
    public TextField txtItemBrand;

    @FXML
    public TextField txtItemId;

    @FXML
    public ComboBox<Enum<CategoryType>> cmbCategoryType;

    @FXML
    public ComboBox<Enum<ItemSizes>> cmbItemSize;

    @FXML
    public ComboBox<String> cmbSupplierId;

    @FXML
    public TextArea txtItemDescription;

    @FXML
    public ComboBox<String> cmbProductType;

    @FXML
    public Button btnDeleteItem;

    @FXML
    public Button btnClear;

    @FXML
    public Button btnAddItem;

    @FXML
    public RadioButton chkSearchBtn;

    @FXML
    public Label lblStaffID;

    Logger log;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblStaffID.setText(setStaffMember());
        btnSearch.setDisable(true);
        loadCategory();
        loadSupplierIds();
    }

    private String setStaffMember() {
        if (LoginPageFormController.staffMember instanceof Employee) {
            Employee employee = (Employee) LoginPageFormController.staffMember;
            return employee.getId();
        } else if (LoginPageFormController.staffMember instanceof Admin) {
            Admin admin = (Admin) LoginPageFormController.staffMember;
            return admin.getId();
        }
        return null;
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        Supplier supplier = supplierService.findById(cmbSupplierId.getValue());
        if (supplier.getItemSet() == null) supplier.setItemSet(new HashSet<Item>());
        if (supplier.getSupplierItemRecord() == null) supplier
                .setSupplierItemRecord(new HashMap<>(0));
        Product product = productService.findByType(cmbProductType.getValue());
        ArrayList<Item> items = new ArrayList<>();
        if (product.getItemSet() == null) product.setItemSet(new HashSet<Item>());
        for (int i = 0; i < Integer.parseInt(txtItemQty.getText()); i++) {
            Item item = new Item(txtItemId.getText(), txtItemBrand.getText(),
                    cmbItemSize.getValue().toString(), product,
                    null, null, null);

            itemService.save(item);
            supplier.getItemSet().add(item);
            items.add(item);
            supplierService.update(supplier);
            product.getItemSet().add(item);
            txtItemId.setText(itemService.setItemId(cmbProductType.getValue()));
        }
        int size = supplier.getSupplierItemRecord().size();
        supplier.getSupplierItemRecord().put(size++, items);
        supplierService.update(supplier);
        product.setProductQuantity(product.getProductQuantity() + Integer.parseInt(txtItemQty.getText()));
        product.setCategory(categoryService.findByType((CategoryType) cmbCategoryType.getValue()));
        productService.update(product);

    }


    //TODO: ready for next product

    @FXML
    public void cmbCategoryOnAction(ActionEvent actionEvent) {
        loadProducts();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Item item = itemService.findById(txtItemId.getText());

        txtItemId.setText(item.getId());
        cmbCategoryType.setValue(Enum.valueOf(CategoryType.class, cmbCategoryType.getValue().toString()));
        cmbProductType.setValue(item.getProduct().toString());
        txtItemDescription.setText(item.getProduct().getProductDescription());
        txtItemPrice.setText(item.getProduct().getProductPrice().toString());
        txtItemQty.setText(item.getProduct().getProductQuantity().toString());
        cmbItemSize.setValue(Enum.valueOf(ItemSizes.class, cmbItemSize.getValue().toString()));

        btnDeleteItem.setDisable(false);
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        //TODO: Update
        //TODO: update quantity
    }

    @FXML
    public void chkSearchModeOnAction(ActionEvent actionEvent) {
        inputDisable(chkSearchBtn.isSelected());
    }

    @FXML
    public void categoryTypeOnAction(ActionEvent actionEvent) {
        loadProducts();
    }

    @FXML
    public void btnDeleteItemOnAction(ActionEvent actionEvent) {
    }

    @FXML
    public void btnClearOnAction(ActionEvent actionEvent) {
    }

    private void loadCategory() {
        cmbCategoryType.setItems(FXCollections
                .observableArrayList(CategoryType.MEN, CategoryType.WOMEN, CategoryType.KIDS, CategoryType.FOOT));
    }

    private void setItemId() {
        txtItemId.setText(itemService.setItemId(cmbProductType.getValue()));
    }

    private void loadProducts() {
        ObservableList<String> productList = FXCollections.observableArrayList();
        for (Product product : productService.getByCategoryType((CategoryType) cmbCategoryType.getValue())) {
            productList.add(product.getProductName());
        }
        System.out.println(cmbProductType);
        cmbProductType.setItems(productList);
    }

    public void inputDisable(Boolean isDisabled) {
        btnSearch.setDisable(!isDisabled);
        btnDeleteItem.setDisable(!isDisabled);
        btnAddItem.setDisable(isDisabled);
        txtItemQty.setDisable(isDisabled);
        txtItemId.setDisable(isDisabled);
        cmbCategoryType.setDisable(isDisabled);
        cmbProductType.setDisable(isDisabled);
        cmbItemSize.setDisable(isDisabled);
    }


    public void cmbProductTypeOnAction(ActionEvent actionEvent) {
        txtItemId.setText(itemService.setItemId(cmbProductType.getValue()));
        loadItemSizes();
    }

    private void loadItemSizes() {
        cmbItemSize.setItems(FXCollections
                .observableArrayList(ItemSizes.XXS, ItemSizes.XS, ItemSizes.S, ItemSizes.M, ItemSizes.L,
                        ItemSizes.XL, ItemSizes.XXL, ItemSizes.XXXL));
    }

    public void loadSupplierIds() {
        cmbSupplierId.setItems(FXCollections.observableArrayList(supplierService.findAll().stream()
                .map(Supplier::getId).collect(Collectors.toList())));
    }

}
