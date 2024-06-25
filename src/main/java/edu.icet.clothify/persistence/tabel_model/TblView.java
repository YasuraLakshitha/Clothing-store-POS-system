package edu.icet.clothify.persistence.tabel_model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TblView {
    private String orderId;
    private String employeeId;
    private String orderDate;
    private String customerId;
    private String customerName;
    private String netAmount;
}
