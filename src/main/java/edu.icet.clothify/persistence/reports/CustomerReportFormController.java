package edu.icet.clothify.persistence.reports;

import edu.icet.clothify.entity.CustomerEntity;
import edu.icet.clothify.util.DBConnection;
import edu.icet.clothify.util.HibernateUtil;
import javafx.fxml.Initializable;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.Session;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerReportFormController implements Initializable {
    public void viewCustomerReportForm() throws JRException {
        String filePath = "src/main/resources/reports/Customer_orders.jrxml";
        try {
            JasperDesign jasperDesign = JRXmlLoader.load(new FileInputStream(filePath));
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, DBConnection.getInstance().getConnection());
            JasperViewer jasperView = new JasperViewer(jasperPrint, false);
        } catch (Exception e) {

        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            viewCustomerReportForm();
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}
