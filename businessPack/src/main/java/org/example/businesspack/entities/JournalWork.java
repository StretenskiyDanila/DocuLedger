package org.example.businesspack.entities;

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
public class JournalWork {

    private Long id;
    private String client;
    private List<Person> person;
    private List<Maintenance> maintenance;
    private Timestamp workDate;

}
