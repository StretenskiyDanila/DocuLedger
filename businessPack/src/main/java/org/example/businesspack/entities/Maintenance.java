package org.example.businesspack.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nameMaintenance;

    @Column(name = "amount")
    private String amountMaintenance;

}
