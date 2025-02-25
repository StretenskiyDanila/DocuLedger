package org.example.businesspack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceDto {

    private Long id;
    private String nameMaintenance;
    private BigInteger amountMaintenance;
    private JournalWorkDto journalWork;

}
