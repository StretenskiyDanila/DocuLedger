package org.example.businesspack.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Maintenance {

    private Long id;
    private String nameMaintenance;
    private BigInteger amountMaintenance;
    private JournalWork journalWork;

}
