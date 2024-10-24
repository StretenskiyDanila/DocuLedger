package org.example.businesspack.dto;

import lombok.Builder;
import lombok.Data;
import org.example.businesspack.entities.Maintenance;

import java.math.BigInteger;

@Builder
@Data
public class MaintenanceDto {

    private Long id;
    private String nameMaintenance;
    private BigInteger amountMaintenance;

    public static MaintenanceDto of(Maintenance maintenance) {
        return MaintenanceDto.builder()
                .id(maintenance.getId())
                .nameMaintenance(maintenance.getNameMaintenance())
                .amountMaintenance(maintenance.getAmountMaintenance())
                .build();
    }

}
