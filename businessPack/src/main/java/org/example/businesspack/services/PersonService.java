package org.example.businesspack.services;

import java.util.List;

import org.example.businesspack.dto.PersonDto;
import org.jooq.Condition;

public interface PersonService {

    Integer save(PersonDto entity);
    List<PersonDto> get(Condition condition);
    PersonDto getById(Integer id);
    void delete();
    void delete(PersonDto entity);
    Integer update(PersonDto updateEntity);

}