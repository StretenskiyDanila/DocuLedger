package org.example.businesspack.dto;

import java.time.LocalDate;

import lombok.*;
import org.example.businesspack.dto.enums.PersonRole;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PersonDto {

    private Integer id;
    private String name;
    private PersonRole role;
    private LocalDate lastUsed;
    private Integer usageCount;
    private String tab;

}
