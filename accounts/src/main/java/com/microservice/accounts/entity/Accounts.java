package com.microservice.accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "accounts")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Accounts extends BaseEntity {
    @Id
    private Long accountNumber;

    private String accountType;
    private String branchAddress;

    @OneToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}
