package edu.icet.clothify.persistence;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.admin.BoServiceAdmin;
import edu.icet.clothify.bo.custom.employee.BoEmployeeService;
import edu.icet.clothify.bo.custom.supplier.BoSupplierService;
import edu.icet.clothify.model.Admin;
import edu.icet.clothify.model.Employee;
import edu.icet.clothify.model.Supplier;
import edu.icet.clothify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RegisterFormController implements Initializable {

    private final BoServiceAdmin adminService = new BoFactory().createBoImpl(BoType.ADMIN);
    private final BoEmployeeService employeeService = new BoFactory().createBoImpl(BoType.EMPLOYEE);
    private final BoSupplierService supplierService = new BoFactory().createBoImpl(BoType.SUPPLIER);
    @FXML
    public Label lblUserId;
    @FXML
    public TextField txtCompany;
    @FXML
    public ComboBox<String> cmbStaffId;
    @FXML
    public ComboBox<String> cmbUserType;
    @FXML
    private ComboBox<String> cmbStaffType;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCmbUserType();
    }

    public void btnSubmitOnAction(ActionEvent actionEvent) throws IOException {
        switch (cmbUserType.getValue()) {
            case "Admin":
                adminService.save(new Admin(
                        lblUserId.getText(),
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText(),
                        null));
                break;

            case "Employee":
                Admin admin = adminService.findById(cmbStaffId.getValue());
                employeeService.save(new Employee(
                        lblUserId.getText(),
                        txtName.getText(),
                        txtEmail.getText(),
                        txtPassword.getText(),
                        null,
                        admin,
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
                        txtCompany.getText(),
                        null,
                        null
                ));
                break;
        }
        JOptionPane.showConfirmDialog(
                null, "Registered Successfully", "Success",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);

        Stage currentStage = (Stage) lblUserId.getScene().getWindow();
        currentStage.close();
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/login-form.fxml"))));
        stage.show();
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
                txtCompany.setDisable(true);
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
                txtCompany.setDisable(false);
                loadStaffType();
                break;
        }
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