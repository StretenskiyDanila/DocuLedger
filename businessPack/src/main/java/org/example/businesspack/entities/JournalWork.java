package org.example.businesspack.entities;

import jakarta.persistence.*;

import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "journal_work")
public class JournalWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String master;

    private String maintenance;

    private String client;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigInteger amountMaintenance;

    @Column(name = "work_date")
    private Timestamp workDate;

}
