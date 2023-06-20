package com.example.atmws.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTx {
    @Id
    @GeneratedValue
    private Long idTx;
    private String operationType;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="account_name", nullable=false)
    private Account account;

    @UpdateTimestamp
    private Timestamp createDate;
}
