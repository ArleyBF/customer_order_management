package com.BF.customer_order_management.models;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Setter
@Getter
@Builder

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the customer", example = "1")
    private Long id;

    @Schema(description = "First name of the customer", example = "John")
    @Column(nullable = false)
    private String firstName;

    @Schema(description = "Last name of the customer", example = "Smith")
    private String lastName;

    @Column(unique = true,nullable = false)
    @Schema(description = "Email of the customer", example = "john.smith@example.com")
    private String email;

    @Schema(description = "Phone number of the customer", example = "123456789")
    private String phone;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY, mappedBy = "customer")
    @Schema(description = "List of orders associated with the customer")
    private List<Order> orders;


}
