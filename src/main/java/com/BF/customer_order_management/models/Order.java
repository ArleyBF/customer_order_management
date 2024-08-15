package com.BF.customer_order_management.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the order", example = "101")
    private Long id;

    @Temporal(TemporalType.DATE)
    @Schema(description = "Date when the order was placed", example = "2024-08-06")
    private Date orderDate;

    @Schema(description = "Total amount of the order", example = "99.99")
    private BigDecimal totalAmount;

    @ManyToOne(targetEntity = Customer.class)
    @JoinColumn(name = "customerId")
    @Schema(description = "Customer who placed the order")
    private Customer customer;

}
