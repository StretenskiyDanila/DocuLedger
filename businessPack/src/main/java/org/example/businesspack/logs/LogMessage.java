package org.example.businesspack.logs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LogMessage {

    START_METHOD("Start method: {}"),
    REQUEST_PARAMETERS("Request parameters: {}"),
    RESULT_LIST_METHOD("Result method only 5 first elements: {}"),
    RESULT_METHOD("Result method: {}"),
    END_METHOD("End method: {}"),

    ERROR_CONNECTION("Error: Couldn't get a connection to the database.\nMessage: {}");

    private final String message;

}
