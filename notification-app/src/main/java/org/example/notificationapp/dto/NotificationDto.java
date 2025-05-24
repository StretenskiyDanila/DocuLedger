package org.example.notificationapp.dto;

import jakarta.validation.constraints.NotNull;
import org.example.notificationapp.annotations.ValidMail;
import org.example.notificationapp.annotations.ValidUserData;

@ValidUserData
public record NotificationDto (

    Long userId,
    String userName,
    @ValidMail
    String userMail,
    String tabName,
    @NotNull(message = "State cannot be null")
    String state,
    @NotNull(message = "Channel cannot be null")
    String channel,
    @NotNull(message = "Enable cannot be null")
    Boolean enable

) {}
