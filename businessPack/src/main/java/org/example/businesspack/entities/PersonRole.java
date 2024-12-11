package org.example.businesspack.entities;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonRole {

    PRODUCER("Producer"),
    CONSUMER("Consumer");

    private final String role;

}
