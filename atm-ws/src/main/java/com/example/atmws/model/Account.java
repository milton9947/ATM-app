package com.example.atmws.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Data
public class Account {
    @Id
    private String accountName;

    private Double balance;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private Timestamp createDate;

    @UpdateTimestamp
    private Timestamp updateDate;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private Set<AccountTx> accountTxs;
}
