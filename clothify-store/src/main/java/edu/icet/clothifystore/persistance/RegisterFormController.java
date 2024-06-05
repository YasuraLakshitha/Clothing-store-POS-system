package edu.icet.clothifystore.persistance;

import com.jfoenix.controls.JFXComboBox;
import edu.icet.clothifystore.bo.BoFactory;
import edu.icet.clothifystore.bo.custom.admin.BoServiceAdmin;
import edu.icet.clothifystore.bo.custom.employee.BoEmployeeService;
import edu.icet.clothifystore.bo.custom.supplier.BoSupplierService;
import edu.icet.clothifystore.model.Admin;
import edu.icet.clothifystore.model.Employee;
import edu.icet.clothifystore.model.Supplier;
import edu.icet.clothifystore.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    @FXML
    public Label lblUserId;

    @FXML
    public TextField txtCompaney;

    @FXML
    public JFXComboBox<String> cmbStaffId;

    @FXML
    private JFXComboBox<String> cmbProductType;

    @FXML
    private JFXComboBox<String> cmbStaffType;

    @FXML
    private JFXComboBox<String> cmbUserType;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    private final BoServiceAdmin adminService = new BoFactory().createBoImpl(BoType.ADMIN);

    private final BoEmployeeService employeeService = new BoFactory().createBoImpl(BoType.EMPLOYEE);

    private final BoSupplierService supplierService = new BoFactory().createBoImpl(BoType.SUPPLIER);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCmbUserType();
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) {
        switch (cmbUserType.getValue()) {
            case "Admin":
                adminService.save(new Admin(
                        lblUserId.getText(),
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText(),
                        null,
                        null));
                break;

            case "Employee":
                employeeService.save(new Employee(
                        lblUserId.getText(),
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText(),
                        null,
                        adminService.findById(cmbStaffId.getValue()),
                        null
                ));
                break;

            case "Supplier":
                supplierService.save(new Supplier(
                        lblUserId.getText(),
                        cmbStaffId.getValue(),
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText(),
                        txtCompaney.getText(),
                        null//TODO add product : BoProductImpl
                ));
                break;
        }
    }

    public void setCmbUserType() {
        ObservableList<String> userList = FXCollections.observableArrayList();
        userList.add("Admin");
        userList.add("Employee");
        userList.add("Supplier");
        cmbUserType.setItems(userList);
    }

    public void cmbUserTypeOnAction(ActionEvent actionEvent) {
        String cmbUserValue = cmbUserType.getValue();
        switch (cmbUserValue) {
            case "Admin":
                cmbStaffType.setDisable(true);
                cmbStaffId.setDisable(true);
                cmbProductType.setDisable(true);
                txtCompaney.setDisable(true);
                lblUserId.setText(adminService.generateAdminId());
                break;
            case "Employee":
                cmbStaffType.setDisable(false);
                cmbStaffId.setDisable(false);
                lblUserId.setText(employeeService.generateEmployeeId());
                loadStaffType();
                break;
            case "Supplier":
                lblUserId.setText(supplierService.generateSupplierId());
                cmbStaffType.setDisable(false);
                cmbStaffId.setDisable(false);
                txtCompaney.setDisable(false);
                cmbProductType.setDisable(false);

                loadStaffType();
                loadProductIds();
                break;
        }
    }

    private void loadProductIds() {
        //TODO Implement ProductsImpl
    }

    private void loadStaffType() {
        ObservableList<String> staffList = FXCollections.observableArrayList();
        if (cmbUserType.getValue().equals("Employee")) staffList.add("Admin");
        else if (cmbUserType.getValue().equals("Supplier")) {
            staffList.add("Admin");
            staffList.add("Employee");
        }
        cmbStaffType.setItems(staffList);
    }

    public void setCmbStaffIds() {
        ObservableList<String> adminIdList = FXCollections.observableArrayList();
        List<String> ids = null;
        System.out.println(cmbStaffType.getValue());
        switch (cmbStaffType.getValue()) {
            case "Admin":
                ids = adminService.loadAdminIds();
                break;
            case "Employee":
                ids = employeeService.loadEmployeeIds();
                break;
        }
        if (ids != null) adminIdList.addAll(ids);
        cmbStaffId.setItems(adminIdList);
    }

    public void cmbStaffTypeOnAction(ActionEvent actionEvent) {
        setCmbStaffIds();
    }
}