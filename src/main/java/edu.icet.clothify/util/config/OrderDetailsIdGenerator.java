package edu.icet.clothify.util.config;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class OrderDetailsIdGenerator implements Serializable {
    private String orderId;
    private String employeeId;
    private String itemId;
}
