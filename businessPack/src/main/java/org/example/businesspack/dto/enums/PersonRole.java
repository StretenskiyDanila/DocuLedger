package org.example.businesspack.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PersonRole {

    PRODUCER("Producer"),
    CONSUMER("Consumer"),
    PASSED("Passed"),
    ACCEPTED("Accepted");

    private final String name;

}
