package edu.icet.clothify.persistence;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.category.BoCategoryService;
import edu.icet.clothify.bo.custom.customer.BoCustomerService;
import edu.icet.clothify.bo.custom.employee.BoEmployeeService;
import edu.icet.clothify.bo.custom.item.BoItemService;
import edu.icet.clothify.bo.custom.order.BoOrderService;
import edu.icet.clothify.bo.custom.order_details.BoOrderDetailsService;
import edu.icet.clothify.bo.custom.payment.BoPaymentService;
import edu.icet.clothify.bo.custom.payment_type.BoPaymentTypeService;
import edu.icet.clothify.bo.custom.product.BoProductService;
import edu.icet.clothify.model.*;
import edu.icet.clothify.persistence.tabel_model.CartTable;
import edu.icet.clothify.util.BoType;
import edu.icet.clothify.util.CategoryType;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;;
import javafx.util.Duration;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
public class OrderPanelFormController implements Initializable {

    private final BoCategoryService categoryService = new BoFactory().createBoImpl(BoType.CATEGORY);

    private final BoProductService productService = new BoFactory().createBoImpl(BoType.PRODUCT);

    private final BoOrderService orderService = new BoFactory().createBoImpl(BoType.ORDER);

    private final BoItemService itemService = new BoFactory().createBoImpl(BoType.ITEM);

    private final BoEmployeeService employeeService = new BoFactory().createBoImpl(BoType.EMPLOYEE);

    private final BoCustomerService customerService = new BoFactory().createBoImpl(BoType.CUSTOMER);

    private final BoOrderDetailsService orderDetailsService = new BoFactory().createBoImpl(BoType.ORDER_DETAILS);

    private final BoPaymentService paymentService = new BoFactory().createBoImpl(BoType.PAYMENT);

    private final BoPaymentTypeService paymentTypeService = new BoFactory().createBoImpl(BoType.PAYMENT_TYPE);
    private final Map<Product, Integer> selectedProductQuantityMap = new HashMap<>();
    private final ObservableList<CartTable> cartTableData = FXCollections.observableArrayList();
    @FXML
    public ComboBox<String> cmbCategoryType;
    @FXML
    public TextField txtCustomerName;
    @FXML
    public TextField txtCustomerId;
    @FXML
    public Button btnSearchCustomer;
    @FXML
    public ComboBox<String> cmbPaymentType;
    @FXML
    private ComboBox<String> cmbProductList;
    @FXML
    private TableColumn<?, ?> colItemId;
    @FXML
    private TableColumn<?, ?> colItemQuantity;
    @FXML
    private TableColumn<?, ?> colItemSize;
    @FXML
    private TableColumn<?, ?> colPrice;
    @FXML
    private TableColumn<?, ?> colProductDescription;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblProductPrice;
    @FXML
    private Label lblNetAmount;
    @FXML
    private Label lblQuantityOnHand;
    @FXML
    private Label lblStaffId;
    @FXML
    private Label lblInvalidQuantity;
    @FXML
    private Label lblTime;
    @FXML
    private TableView<CartTable> tblCart;
    @FXML
    private TextField txtCustomerContact;
    @FXML
    private TextField txtCustomerEmail;
    @FXML
    private TextField txtItemQuantity;
    @FXML
    private TextField txtOrderId;
    private Set<Item> itemSet = null;
    private Set<OrderDetails> orderDetailsSet = new HashSet<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lblStaffId.setText(setStaffMember());
        loadCategoryType();
        loadDateAndTime();
        txtCustomerId.setEditable(false);
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
    void btnAddToCartOnAction(ActionEvent event) {
        loadData();
        loadPaymentType();
        lblQuantityOnHand.setText(
                Integer.parseInt(lblQuantityOnHand.getText()) - Integer.parseInt(txtItemQuantity.getText()) + "");
        selectedProductQuantityMap.put(productService.findByType(cmbProductList.getValue()), Integer.parseInt(lblQuantityOnHand.getText()));
    }

    private void loadDateAndTime() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(dateFormat.format(date));
        Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, evt -> {
            LocalTime localTime = LocalTime.now();
            lblTime.setText(localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond());
        }),
                new KeyFrame(Duration.seconds(1))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
//TODO:incomplete
    }

    @FXML
    void btnCancelOrderOnAction(ActionEvent event) {
        clearFields();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        Employee employee = employeeService.findById(lblStaffId.getText());
        Customer customer = customerService.findById(txtCustomerId.getText());
        if (customer == null) {
            customer = new Customer(txtCustomerId.getText(), txtCustomerName.getText(), txtCustomerEmail.getText(),
                    txtCustomerContact.getText(), new HashSet<>());
            customerService.save(customer);
        }

        PaymentType paymentType = paymentTypeService.findByType(cmbPaymentType.getValue());

        Payment payment = new Payment(0, Double.parseDouble(lblNetAmount.getText()),
                paymentType, null);

        Order order = new Order();
        order.setId(txtOrderId.getText());
        order.setCustomer(customerService.findById(txtCustomerId.getText()));
        order.setEmployee(employee);
        order.setPayment(payment);
        order.setItemSet(new HashSet<>());
        orderService.update(order);

        order.setPayment(payment);

        payment.setOrder(order);
        paymentService.update(payment);

        orderDetailsSet = new HashSet<>();
        itemSet.forEach(item -> {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setOrder(order);
            orderDetails.setEmployee(employee);
            orderDetails.setOrderDate(LocalDate.parse(lblDate.getText()));
            orderDetails.setItem(item);

            item.setOrder(order);
            item.setProduct(productService.findByType(cmbProductList.getValue()));
            itemService.update(item);

            orderDetailsService.save(orderDetails);
            orderDetailsSet.add(orderDetails);
            order.getItemSet().add(item);
        });

        customer.getOrderSet().add(order);
        customerService.update(customer);

        Product product = productService.findByType(cmbProductList.getValue());
        product.setProductQuantity(Integer.parseInt(lblQuantityOnHand.getText()));

        productService.update(product);
        clearFields();
    }

    private void clearFields() {
        txtCustomerName.clear();
        txtCustomerEmail.clear();
        txtCustomerContact.clear();
        txtItemQuantity.clear();
        txtOrderId.clear();
        lblQuantityOnHand.setText("");
        tblCart.getItems().clear();
        lblNetAmount.setText("");
    }

    @FXML
    void btnViewOrdersOnAction(ActionEvent event) {
//TODO:incomplete
    }

    @FXML
    public void cmbItemListOnAction(ActionEvent actionEvent) {
        txtOrderId.setText(orderService.generateOrderId());
        lblQuantityOnHand.setText(productService.findByType(
                cmbProductList.getValue()).getProductQuantity().toString());
        lblProductPrice.setText(
                productService.findByType(cmbProductList.getValue()).getProductPrice().toString());
    }

    @FXML
    public void cmbCategoryTypeOnAction(ActionEvent actionEvent) {
        List<Product> productList = productService.getByCategoryType(CategoryType.valueOf(cmbCategoryType.getValue()));
        ObservableList<String> productTypes = FXCollections.observableArrayList();
        productList.forEach(product -> productTypes.add(product.getProductName()));
        cmbProductList.setItems(productTypes);
    }

    @FXML
    public void txtItemQuantityOnAction(ActionEvent actionEvent) {
        if (Integer.parseInt(txtItemQuantity.getText()) > Integer.parseInt(lblQuantityOnHand.getText())
                || Integer.parseInt(txtItemQuantity.getText()) < 0) {
            txtItemQuantity.clear();
            lblInvalidQuantity.setVisible(true);
        } else {
            lblInvalidQuantity.setVisible(false);
        }
    }

    @FXML
    public void btnSearchCustomerOnAction(ActionEvent actionEvent) {
        Customer customer = customerService.findById(txtCustomerId.getText());
        if (customer == null) {
            JOptionPane.showMessageDialog(null, "Customer not exist", "Inane warning", JOptionPane.ERROR_MESSAGE);
            return;
        }

        txtCustomerId.setText(customer.getId());
        txtCustomerName.setText(customer.getCustomerName());
        txtCustomerEmail.setText(customer.getCustomerEmail());
        txtCustomerName.setText(customer.getCustomerContact());

        txtCustomerId.setEditable(false);
        txtCustomerName.setEditable(false);
        txtCustomerEmail.setEditable(false);
    }

    @FXML
    public void cmbPaymentTypeOnAction(ActionEvent actionEvent) {
    }

    public void txtCustomerNameOnAction(ActionEvent actionEvent) {
        txtCustomerId.setText(customerService.generateCustomerId());
    }

    private void loadCategoryType() {
        List<Category> categoryList = categoryService.findAll();
        ObservableList<String> categories = FXCollections.observableArrayList();
        categoryList.forEach(category -> categories.add(category.getCategoryType()));
        cmbCategoryType.setItems(categories);
    }

    private void bindValues() {
        colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
        colProductDescription.setCellValueFactory(new PropertyValueFactory<>("itemDescription"));
        colItemSize.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        colItemQuantity.setCellValueFactory(new PropertyValueFactory<>("itemQuantity"));
        colPrice.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
    }

    private void loadData() {
        Double netAmount = 0.0;
        bindValues();
        List<Item> itemList = itemService.getAllItemsByProductType(cmbProductList.getValue());
        itemSet = new HashSet<>();
        for (int i = 0; i < Integer.parseInt(txtItemQuantity.getText()); i++) {
            Item item = itemList.get(i);
            cartTableData.add(new CartTable(
                    item.getId(),
                    item.getProduct().getProductDescription(),
                    item.getItemSize(),
                    null,
                    item.getProduct().getProductPrice()
            ));
            netAmount += item.getProduct().getProductPrice();
            itemSet.add(item);
            tblCart.setItems(cartTableData);
            lblNetAmount.setText(netAmount.toString());
        }
    }

    private void loadPaymentType() {
        List<PaymentType> paymentTypeList = paymentTypeService.findAll();
        ObservableList<String> paymentTypes = FXCollections.observableArrayList();
        paymentTypeList.forEach(paymentType -> paymentTypes.add(paymentType.getType()));
        cmbPaymentType.setItems(paymentTypes);
    }

    @FXML
    public void radioSearchModeOnAction(ActionEvent actionEvent) {
        btnSearchCustomer.setDisable(false);
        txtCustomerId.setEditable(true);
    }
}
