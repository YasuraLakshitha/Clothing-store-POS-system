package edu.icet.clothifystore.persistance;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothifystore.bo.BoFactory;
import edu.icet.clothifystore.bo.custom.category.CategoryService;
import edu.icet.clothifystore.bo.custom.item.ItemBoService;
import edu.icet.clothifystore.util.BoType;
import edu.icet.clothifystore.util.CategoryType;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemFormController implements Initializable {

    private final ItemBoService itemBoService = new BoFactory().createBoImpl(BoType.ITEM);

    private final CategoryService categoryService = new BoFactory().createBoImpl(BoType.CATEGORY);

    @FXML
    public Button btnSearch;

    @FXML
    public Label lblSupplierId;

    @FXML
    public JFXComboBox<String> cmbProductType;

    @FXML
    private JFXComboBox<Enum<CategoryType>> cmbCategory;

    @FXML
    private Label lblProductId;

    @FXML
    private TextField txtProductQty;

    @FXML
    private TextField txtProductName;

    @FXML
    private TextField txtProductPrice;

    @FXML
    private TextField txtProductSize;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSearch.setDisable(true);
        loadCategory();
    }

    @FXML
    void btnAddProductOnAction(ActionEvent event) {

        //TODO: save product
        //TODO: update quantity
        //TODO: update category set
        //TODO: ready for next product
    }

    @FXML
    public void cmbCategoryOnAction(ActionEvent actionEvent) {
        setItemId();
    }

    @FXML
    void btnSearchModeOnAction(ActionEvent event) {
        //TODO: Enable search
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
        //TODO: load products
    }

    private void loadCategory() {
        cmbCategory.setItems(FXCollections.observableArrayList(
                CategoryType.MEN,
                CategoryType.WOMEN,
                CategoryType.KIDS,
                CategoryType.FOOT
        ));
    }

    public void setItemId() {
        lblProductId.setText(itemBoService.generateId(cmbCategory.getValue()));
    }
}





