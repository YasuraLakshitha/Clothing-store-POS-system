package edu.icet.clothifystore.persistance;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothifystore.bo.BoFactory;
import edu.icet.clothifystore.bo.custom.category.CategoryService;
import edu.icet.clothifystore.bo.custom.item.ItemBoService;
import edu.icet.clothifystore.bo.custom.product.BoProductService;
import edu.icet.clothifystore.model.Item;
import edu.icet.clothifystore.model.Product;
import edu.icet.clothifystore.util.BoType;
import edu.icet.clothifystore.util.CategoryType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    private final ItemBoService itemService = new BoFactory().createBoImpl(BoType.ITEM);

    private final CategoryService categoryService = new BoFactory().createBoImpl(BoType.CATEGORY);

    private final BoProductService productService = new BoFactory().createBoImpl(BoType.PRODUCT);

    @FXML
    public Button btnSearch;

    @FXML
    public Label lblSupplierId;

    @FXML
    public JFXComboBox<String> cmbProductType;

    @FXML
    public Label lblItemId;

    @FXML
    public TextField txtItemName;

    @FXML
    public TextField txtItemSize;

    @FXML
    public TextField txtItemPrice;

    @FXML
    public TextField txtItemQty;

    @FXML
    private JFXComboBox<Enum<CategoryType>> cmbCategory;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSearch.setDisable(true);
        loadCategory();
    }

    @FXML
    void btnAddItemOnAction(ActionEvent event) {
        itemService.save(new Item(
                lblItemId.getText(),
                txtItemName.getText(),
                Integer.parseInt(txtItemSize.getText()),
                Double.parseDouble(txtItemPrice.getText()),
                Integer.parseInt(txtItemQty.getText()),
                productService.findByType(cmbProductType.getValue()),
                null,//TODO: find order By Id
                null,//TODO: add to supplieritemset
                null//TODO: add to  orderDetailsEntitySet
        ));
        //TODO: save product
        //TODO: update quantity
        //TODO: update category set
        //TODO: ready for next product
    }

    @FXML
    public void cmbCategoryOnAction(ActionEvent actionEvent) {
        loadProducts();
    }


    @FXML
    void btnSearchModeOnAction(ActionEvent event) {
        btnSearch.setDisable(false);
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        //TODO: Generate next id
        //TODO: Search by product, by category, by id
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        //TODO: Update
        //TODO: update quantity
    }

    @FXML
    public void cmbProductTypeOnAction(ActionEvent actionEvent) {
        setItemId();
    }

    private void loadCategory() {
        cmbCategory.setItems(FXCollections.observableArrayList(
                CategoryType.MEN,
                CategoryType.WOMEN,
                CategoryType.KIDS,
                CategoryType.FOOT
        ));
    }

    private void setItemId() {
        lblItemId.setText(itemService.setItemId(cmbProductType.getValue()));
    }

    private void loadProducts() {
        ObservableList<String> productList = FXCollections.observableArrayList();
        for (Product product : productService.getByCategoryType(cmbCategory.getValue())) {
            productList.add(product.getProductName());
        }
        cmbProductType.setItems(productList);
    }

}





