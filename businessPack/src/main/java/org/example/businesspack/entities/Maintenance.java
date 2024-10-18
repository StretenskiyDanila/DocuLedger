package org.example.businesspack.entities;

import jakarta.persistence.*;

import java.math.BigInteger;

@Entity
@Table(name = "maintenance")
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nameMaintenance;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigInteger amountMaintenance;

}
