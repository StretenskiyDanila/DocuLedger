package org.example.businesspack.services.impl;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.Person;
import org.example.businesspack.repositories.PersonRepository;
import org.example.businesspack.repositories.impl.PersonRepositoryImpl;
import org.example.businesspack.services.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements Service<PersonDto> {

    private final PersonRepository personRepository = new PersonRepositoryImpl();

    @Override
    public Long save(PersonDto entity) {
        Person person = PersonDto.to(entity);
        Long id = null;
        try {
            id = personRepository.save(person);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    @Override
    public List<PersonDto> get(String... parameter) {
        List<PersonDto> models = new ArrayList<>();
        try {
            List<Person> accounts = personRepository.get(parameter);
            if (accounts != null) {
                models = accounts.stream()
                        .map(PersonDto::of)
                        .collect(Collectors.toList());
            } else {
                return models;
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return models;
    }

    @Override
    public Long update(PersonDto entity) {
        Person personUpdate = PersonDto.to(entity);
        Long id = null;
        
        try {
            if (personUpdate.getId() != null) { 
                id = personRepository.update(personUpdate);
            } else {
                id = personRepository.save(personUpdate);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return id;
    }

    @Override
    public void delete() {
        try {
            personRepository.delete();
            personRepository.update();
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

    @Override
    public void delete(PersonDto entity) {
       throw new UnsupportedOperationException("Not support operation");   
    }


}
