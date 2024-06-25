package edu.icet.clothify.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import edu.icet.clothify.util.config.OrderDetailsIdGenerator;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "order_details")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString(onlyExplicitlyIncluded = true)
public class OrderDetailsEntity {
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Include
    private Integer id;
    private LocalDate orderDate;

    @ManyToOne
    // @MapsId("employeeId")
    @JoinColumn(name = "employee_id")
    private EmployeeEntity employeeEntity;

    @ManyToOne
    //  @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;

    @ManyToOne
//    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private ItemEntity itemEntity;
}
