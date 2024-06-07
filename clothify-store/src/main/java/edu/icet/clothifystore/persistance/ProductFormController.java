package edu.icet.clothifystore.persistance;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothifystore.bo.BoFactory;
import edu.icet.clothifystore.bo.custom.product.ProductBoService;
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

public class ProductFormController implements Initializable {

    private final ProductBoService ProductService = new BoFactory().createBoImpl(BoType.PRODUCT);
    @FXML
    public Button btnSearch;
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
        System.out.println("asdasd");
        setProductId();
    }

    @FXML
    void btnSearchModeOnAction(ActionEvent event) {
        //TODO: Enable search
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        //TODO: Generate next id
        //TODO: Search by id
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        //TODO: Update
        //TODO: update quantity
    }

    private void loadCategory() {
        cmbCategory.setItems(FXCollections.observableArrayList(
                CategoryType.MEN,
                CategoryType.WOMEN,
                CategoryType.KIDS,
                CategoryType.FOOT
        ));
    }

    public void setProductId() {
        lblProductId.setText(ProductService.generateId(cmbCategory.getValue()));
    }
}





