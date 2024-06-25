package edu.icet.clothify.persistence;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.admin.BoServiceAdmin;
import edu.icet.clothify.bo.custom.employee.BoEmployeeService;
import edu.icet.clothify.bo.custom.supplier.BoSupplierService;
import edu.icet.clothify.model.Admin;
import edu.icet.clothify.model.Employee;
import edu.icet.clothify.model.Supplier;
import edu.icet.clothify.util.BoType;
import edu.icet.clothify.util.email_to_sms_gatewaylayer.OtpGateway;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Getter
@Setter
public class LoginPageFormController implements Initializable {

    public static Object staffMember = null;
    private final BoServiceAdmin adminService = new BoFactory().createBoImpl(BoType.ADMIN);
    private final BoEmployeeService employeeService = new BoFactory().createBoImpl(BoType.EMPLOYEE);
    private final BoSupplierService supplierService = new BoFactory().createBoImpl(BoType.SUPPLIER);
    private final OtpGateway otpGateway = new OtpGateway();
    @FXML
    public Button btnChangePassword;
    @FXML
    private ComboBox<String> cmbUserType;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtPassword;
    private DefaultDashBoardFromController dashBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cmbUserType.setItems(FXCollections.observableArrayList("Admin", "Employee"));
    }

    @FXML
    void btnChangePasswordOnAction(ActionEvent event) {
        switch (cmbUserType.getValue()) {
            case "Admin":
                Admin admin = adminService.findAdminByEmail(txtEmail.getText());
                if (admin != null) {
                    if (otpGateway.sendOtp(admin.getAdminEmail())) {
                        txtPassword.setPromptText("Enter new password here");
                        btnChangePassword.setText("Change Password");
                        handlePasswordChange(admin);
                    }
                }
                break;
            case "Employee":
                Employee employee = employeeService.findEmployeeByEmail(txtEmail.getText());
                if (employee != null) {
                    if (otpGateway.sendOtp(employee.getEmployeeEmail())) {
                        txtPassword.setPromptText("Enter new password here");
                        btnChangePassword.setText("Change Password");
                        handlePasswordChange(employee);
                    }
                }
                break;
            case "Supplier":
                Supplier supplier = supplierService.findSupplierByEmail(txtEmail.getText());
                if (supplier != null) {
                    if (otpGateway.sendOtp(supplier.getSupplierEmail())) {
                        txtPassword.setPromptText("Enter new password here");
                        btnChangePassword.setText("Change Password");
                        handlePasswordChange(supplier);
                    }
                }
                break;
        }
    }

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        switch (cmbUserType.getValue()) {
            case "Admin":
                Admin admin = adminService.findAdminByEmailAndPassword(txtEmail.getText(), txtPassword.getText());
                if (admin != null) {
                    staffMember = admin;
                    Stage cusrrentStage = (Stage) txtEmail.getScene().getWindow();
                    cusrrentStage.close();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Defalt-dashboard-form.fxml"))));
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect UserName or password");
                    txtEmail.clear();
                    txtPassword.clear();
                }
                break;

            case "Employee":
                Employee employee = employeeService.findByEmployeeByEmailAndPassword(txtEmail.getText(), txtPassword.getText());
                if (employee != null) {
                    staffMember = employee;
                    Stage cusrrentStage = (Stage) txtEmail.getScene().getWindow();
                    cusrrentStage.close();
                    Stage stage = new Stage();
                    stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Defalt-dashboard-form.fxml"))));
                    stage.show();
                } else {
                    JOptionPane.showMessageDialog(null, "Incorrect UserName or password");
                    txtEmail.clear();
                    txtPassword.clear();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + cmbUserType.getValue());
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) throws IOException {
        Stage cusrrentStage = (Stage) txtEmail.getScene().getWindow();
        cusrrentStage.close();
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
