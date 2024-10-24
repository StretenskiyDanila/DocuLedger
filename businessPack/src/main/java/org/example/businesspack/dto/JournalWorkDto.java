package org.example.businesspack.dto;

import lombok.Builder;
import lombok.Data;
import org.example.businesspack.entities.JournalWork;
import org.example.businesspack.entities.Maintenance;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;

@Builder
@Data
public class JournalWorkDto {

    private Long id;
    private List<MasterDto> master;
    private List<MaintenanceDto> maintenance;
    private String client;
    private BigInteger amountMaintenance;
    private Timestamp workDate;

    public static JournalWorkDto of(JournalWork journalWork) {
        return JournalWorkDto.builder()
                .id(journalWork.getId())
                .master(journalWork.getMaster().stream().map(MasterDto::of).toList())
                .maintenance(journalWork.getMaintenance().stream().map(MaintenanceDto::of).toList())
                .client(journalWork.getClient())
                .workDate(journalWork.getWorkDate())
                .build();
    }

}
