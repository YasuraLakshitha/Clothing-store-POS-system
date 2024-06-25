package edu.icet.clothify.model;

import edu.icet.clothify.util.config.OrderDetailsIdGenerator;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class OrderDetails {
    @EqualsAndHashCode.Include
    @ToString.Include
    private Integer id;

    private LocalDate orderDate;

    private Employee employee;

    private Order order;

    private Item item;
}
