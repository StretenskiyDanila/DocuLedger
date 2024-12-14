package org.example.businesspack.entities;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Person extends Table{

    private Long id;
    private String name;
    private PersonRole role;
    private LocalDate lastUsed;
    private Integer usageCount;

    @Override
    public String getTableName() {
        return "person";
    }

}
