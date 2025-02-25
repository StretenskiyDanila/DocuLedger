package org.example.businesspack.dto;

import java.time.LocalDate;

import org.example.businesspack.dto.enums.PersonRole;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDto {

    private Integer id;
    private String name;
    private PersonRole role;
    private LocalDate lastUsed;
    private Integer usageCount;
    private String tab;

}
