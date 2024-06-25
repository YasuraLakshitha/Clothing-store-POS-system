package edu.icet.clothify.persistence;

import edu.icet.clothify.bo.BoFactory;
import edu.icet.clothify.bo.custom.customer.BoCustomerService;
import edu.icet.clothify.bo.custom.order.BoOrderService;
import edu.icet.clothify.bo.custom.order_details.BoOrderDetailsService;
import edu.icet.clothify.bo.custom.product.BoProductService;
import edu.icet.clothify.model.Customer;
import edu.icet.clothify.model.Order;
import edu.icet.clothify.model.OrderDetails;
import edu.icet.clothify.model.Product;
import edu.icet.clothify.persistence.tabel_model.TblProductQuantity;
import edu.icet.clothify.persistence.tabel_model.TblView;
import edu.icet.clothify.util.BoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;


public class OrderViewFormController implements Initializable {

    private static final Logger log = LoggerFactory.getLogger(OrderViewFormController.class);

    private final BoCustomerService customerService = new BoFactory().createBoImpl(BoType.CUSTOMER);

    private final BoOrderService orderService = new BoFactory().createBoImpl(BoType.ORDER);

    private final BoOrderDetailsService orderDetailsService = new BoFactory().createBoImpl(BoType.ORDER_DETAILS);

    private final BoProductService productService = new BoFactory().createBoImpl(BoType.PRODUCT);

    @FXML
    public RadioButton radioCustomer;

    @FXML
    public RadioButton radioOrder;

    public TableView<TblProductQuantity> tblProduct;

    @FXML
    private ComboBox<String> cmbSearch;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colNetAmount;

    @FXML
    private TableColumn<?, ?> colOrderDate;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colProduct;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableView<TblView> tblView;

    @FXML
    private TextField txtId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        radioCustomer.setSelected(true);
        loadIds();
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        Object obejct = null;
        if (radioCustomer.isSelected()) {
            obejct = customerService.findById(cmbSearch.getValue());
        } else if (radioOrder.isSelected()) {
            obejct = orderService.findById(cmbSearch.getValue());
        }
        loadTableData(obejct);
    }

    private void bindTableValues() {
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colNetAmount.setCellValueFactory(new PropertyValueFactory<>("netAmount"));
        colOrderDate.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
    }

    private void loadTableData(Object dataObject) {
        bindTableValues();
        ObservableList<TblView> tblData = FXCollections.observableArrayList();
        TblView tableView = new TblView();
        if (dataObject instanceof Customer) {
            Customer customer = (Customer) dataObject;
            txtId.setText(customer.getId());
            customer.getOrderSet().forEach(order -> {
                tableView.setOrderId(order.getId());
                tableView.setEmployeeId(order.getEmployee().getId());
                tableView.setOrderDate("2020-05-02");
                tableView.setCustomerId(customer.getId());
                tableView.setCustomerName(customer.getCustomerName());
                tableView.setNetAmount("67");
                tblData.add(tableView);
            });
        } else if (dataObject instanceof Order) {
            Order order = (Order) dataObject;
            tableView.setOrderId(order.getId());
            tableView.setEmployeeId(order.getEmployee().getId());
            tableView.setOrderDate("2020-05-02");
            tableView.setCustomerId(order.getCustomer().getId());
            tableView.setCustomerName(order.getCustomer().getCustomerName());
            tableView.setNetAmount("67");
            tblData.add(tableView);
            loadProductTable(order);
        }
        tblView.setItems(tblData);
    }

    private void loadProductTable(Order order) {
        bindProductTableColumns();
        ObservableList<TblProductQuantity> tblData = FXCollections.observableArrayList();
        List<OrderDetails> orderDetailsList = orderDetailsService.findByOrder(order);
        TblProductQuantity tblProductQuantity = new TblProductQuantity();

        for (OrderDetails orderDetail : orderDetailsList) {
            List<Product> byItem = productService.findByItem(orderDetail.getItem());
            HashSet<Product> productSet = new HashSet<>(byItem);
            byItem.forEach(product -> {
                tblProductQuantity.setProductName(product.getProductName());
                tblProductQuantity.setQuantity(product.getProductQuantity());
                tblData.add(tblProductQuantity);
                tblProduct.setItems(tblData);
            });
        }
    }

    private void bindProductTableColumns() {
        colProduct.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
    }

    @FXML
    void cmbSearchOnAction(ActionEvent event) {
        txtId.setDisable(true);
    }

    @FXML
    public void radioCustomerOnAction(ActionEvent actionEvent) {
        radioOrder.setSelected(false);
        cmbSearch.setPromptText("Search Customer");
        txtId.setPromptText("Customer ID");
        loadIds();
    }

    @FXML
    public void radioOrderOnAction(ActionEvent actionEvent) {
        radioCustomer.setSelected(false);
        cmbSearch.setPromptText("Search Order");
        txtId.setPromptText("Order ID");
        loadIds();
    }

    public void loadIds() {
        ObservableList<String> IdList = FXCollections.observableArrayList();
        if (radioCustomer.isSelected()) {
            for (Customer customer : customerService.findAll()) IdList.add(customer.getId());
        } else if (radioOrder.isSelected()) {
            for (Order order : orderService.findAll()) IdList.add(order.getId());
        }
        cmbSearch.setItems(IdList);
    }

    @FXML
    public void txtIdOnKeyTyped(KeyEvent keyEvent) {
        cmbSearch.setDisable(true);
    }
}
