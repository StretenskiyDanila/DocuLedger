package org.example.businesspack.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "journal_work")
public class JournalWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String master;

    private String maintenance;

    private String client;

    private String amountMaintenance;

}
