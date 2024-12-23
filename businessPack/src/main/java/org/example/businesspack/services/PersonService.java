package org.example.businesspack.services;

import java.util.List;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.enums.PersonRole;;

public interface PersonService {

    Long save(PersonDto entity);
    List<PersonDto> get(PersonRole role);
    void delete();
    Long update(PersonDto updateEntity);

}
