package org.example.notificationapp.dto;


public record NotificationDto (

    Long userId,
    String userName,
    String tabName,
    String state

) {}
