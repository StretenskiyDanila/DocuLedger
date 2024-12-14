package org.example.businesspack.dto;

import lombok.Builder;
import lombok.Data;
import org.example.businesspack.entities.Person;
import org.example.businesspack.entities.PersonRole;

@Builder
@Data
public class PersonDto {

    private Long id;
    private String name;
    private PersonRole role;

    public static PersonDto of(Person person) {
        return PersonDto.builder()
                .id(person.getId())
                .name(person.getName())
                .role(person.getRole())
                .build();
    }

    public static Person to(PersonDto personDto) {
        return Person.builder()
                .id(personDto.getId())
                .name(personDto.getName())
                .role(personDto.getRole())
                .build();
    }

}
