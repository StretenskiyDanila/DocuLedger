package org.example.businesspack.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Builder
@Getter
@Table(name = "maintenance")
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String nameMaintenance;

    @Column(name = "amount", precision = 10, scale = 2)
    private BigInteger amountMaintenance;

    @ManyToOne()
    @JoinColumn(name = "maintenance_id", referencedColumnName = "id")
    private JournalWork journalWork;

}
