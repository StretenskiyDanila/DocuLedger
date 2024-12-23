package org.example.businesspack.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

import org.example.businesspack.entities.Person;
import org.example.businesspack.entities.enums.PersonRole;

@Builder
@Data
public class PersonDto {

    private Long id;
    private String name;
    private PersonRole role;
    private LocalDate lastUsed;
    private Integer usageCount;

    public static PersonDto of(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .role(person.getRole())
                .lastUsed(person.getLastUsed())
                .usageCount(person.getUsageCount())
                .build();
    }

    public static Person to(PersonDto personDto) {
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .role(personDto.getRole())
                .lastUsed(personDto.getLastUsed())
                .usageCount(personDto.getUsageCount())
                .build();
    }

}
