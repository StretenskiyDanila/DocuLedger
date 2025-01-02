package org.example.businesspack.entities;

import java.time.LocalDate;

import org.example.businesspack.entities.enums.PersonRole;

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
    private String tab;

    @Override
    public String getTableName() {
        return "person";
    }

}
