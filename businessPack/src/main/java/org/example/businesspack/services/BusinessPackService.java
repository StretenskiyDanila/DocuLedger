package org.example.businesspack.services;

import org.example.businesspack.entities.Person;

public interface BusinessPackService {

    Person addMaster(Person person);

    Person getMaster(Long id);

    Person getMaster();

}
