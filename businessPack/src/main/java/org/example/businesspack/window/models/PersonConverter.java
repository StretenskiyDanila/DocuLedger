package org.example.businesspack.window.models;

import org.example.businesspack.dto.PersonDto;
import org.example.businesspack.entities.PersonRole;
import org.example.businesspack.services.PersonService;
import org.example.businesspack.services.impl.PersonServiceImpl;

import javafx.util.StringConverter;

public class PersonConverter extends StringConverter<PersonDto> {

    private final PersonService personService = new PersonServiceImpl();
    private int personId;

    @Override
    public PersonDto fromString(String arg0) {
        PersonDto person;

        if ()
        person = PersonDto.builder() //TODO: спросить чатик про это
        .id(personId)
        .
        .build();

        return person;
    }

    @Override
    public String toString(PersonDto arg0) {
        return arg0 == null ? null : arg0.getName();
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

}
