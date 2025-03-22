package org.example.notificationapp.dto;


public record NotificationDto (

    Long userId,
    String userName,
    String userMail,
    String tabName,
    String state,
    String channel

) {}
