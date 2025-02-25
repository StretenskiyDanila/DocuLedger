package org.example.businesspack.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JournalWorkDto {

    private Long id;
    private String client;
    private List<PersonDto> person;
    private List<MaintenanceDto> maintenance;
    private Timestamp workDate;

}
