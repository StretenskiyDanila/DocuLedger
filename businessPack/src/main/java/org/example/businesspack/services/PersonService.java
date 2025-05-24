package org.example.businesspack.services;

import org.example.businesspack.dto.PersonDto;
import org.jooq.Condition;

import java.util.List;

public interface PersonService {

    Integer save(PersonDto entity);
    List<PersonDto> get(Condition condition);
    PersonDto getById(Integer id);
    void delete();
    void delete(PersonDto entity);
    Integer update(PersonDto updateEntity);

}