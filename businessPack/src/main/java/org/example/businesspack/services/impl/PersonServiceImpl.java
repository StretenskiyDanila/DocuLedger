package org.example.businesspack.services.impl;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.Person;
import org.example.businesspack.repositories.PersonRepository;
import org.example.businesspack.repositories.TableRepository;
import org.example.businesspack.services.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PersonServiceImpl implements Service<PersonDto> {

    private final TableRepository<Person> personRepository = new PersonRepository();

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
    public List<PersonDto> get() {
        List<PersonDto> models = new ArrayList<>();
        try {
            List<Person> accounts = personRepository.getAll();
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
    public void delete(PersonDto entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Long update(PersonDto currentEntity, PersonDto updateEntity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete() {
        try {
            personRepository.delete(null);
        } catch (SQLException e) {
            e.printStackTrace();
        };
    }

}
