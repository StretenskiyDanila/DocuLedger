package org.example.notificationapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.notificationapp.dto.NotificationDto;
import org.example.notificationapp.mapper.NotificationMapper;
import org.example.notificationapp.model.Notification;
import org.example.notificationapp.service.NotificationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/add")
    public void addNotificationSet(@RequestBody NotificationDto notificationDto) {
        /*Notification notification = NotificationMapper.toEntity(notificationDto);
        notificationService.addNotification(notification);*/
    }

    @PostMapping("/change")
    public void changeNotification(@RequestBody NotificationDto notificationDto) {
        /*Notification notification = NotificationMapper.toEntity(notificationDto);
        notificationService.changeNotification(notification);*/
    }



}
