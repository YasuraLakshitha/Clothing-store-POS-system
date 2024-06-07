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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageFormController implements Initializable {

    private final BoServiceAdmin adminService = new BoFactory().createBoImpl(BoType.ADMIN);

    private final BoEmployeeService employeeService = new BoFactory().createBoImpl(BoType.EMPLOYEE);

    private final BoSupplierService supplierService = new BoFactory().createBoImpl(BoType.SUPPLIER);


    @FXML
    public Button btnChangePassword;

    @FXML
    private JFXComboBox<String> cmbUserType;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbUserType.setItems(FXCollections.observableArrayList("Admin", "Employee"));
    }

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {

        //TODO OTP code validation

        txtPassword.setPromptText("Enter new password here");
        btnChangePassword.setText("Change Password");
        switch (cmbUserType.getValue()) {
            case "Admin":
                Admin admin = adminService.findAdminByEmail(txtEmail.getText());
                if (admin != null) handlePasswordChange(admin);
                break;
            case "Employee":
                Employee employee = employeeService.findEmployeeByEmail(txtEmail.getText());
                if (employee != null) handlePasswordChange(employee);
                break;
            case "Supplier":
                Supplier supplier = supplierService.findSupplierByEmail(txtEmail.getText());
                if (supplier != null) handlePasswordChange(supplier);
                break;

        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        switch (cmbUserType.getValue()) {
            case "Admin":
                if (adminService.findAdminByEmailAndPassword(txtEmail.getText(), txtPassword.getText()) != null) {
                    //TODO direct to admin user panel
                }
                break;

            case "Employee":
                if (employeeService.findByEmployeeByEmailAndPassword(txtEmail.getText(), txtPassword.getText()) != null) {
                    //TODO direct to default user panel
                }
                break;
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Register-form.fxml"))));
        stage.show();
    }

    public <T> void handlePasswordChange(T object) {
        btnChangePassword.addEventHandler(ActionEvent.ACTION, event -> {
            if (object instanceof Admin) {
                Admin admin = (Admin) object;
                admin.setAdminPassword(txtPassword.getText());
                adminService.update(admin);
            } else if (object instanceof Employee) {
                Employee employee = (Employee) object;
                employee.setEmployeePassword(txtPassword.getText());
                employeeService.update(employee);
            } else {
                Supplier supplier = (Supplier) object;
                supplier.setSupplierPassword(txtPassword.getText());
                supplierService.update(supplier);
            }
        });
    }

}
