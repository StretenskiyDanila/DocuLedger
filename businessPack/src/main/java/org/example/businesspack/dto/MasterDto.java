package org.example.businesspack.dto;

import lombok.Builder;
import lombok.Data;
import org.example.businesspack.entities.Person;

@Builder
@Data
public class MasterDto {

    private Long id;
    private String name;
    private String position;

    public static MasterDto of(Person person) {
        return MasterDto.builder()
                .id(person.getId())
                .name(person.getName())
                .position(person.getPost())
                .build();
    }

}
