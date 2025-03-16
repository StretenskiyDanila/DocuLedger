package org.example.notificationapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.notificationapp.dto.NotificationDto;
import org.example.notificationapp.mapper.NotificationMapper;
import org.example.notificationapp.entity.Notification;
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
    private final NotificationMapper notificationMapper;

    @PostMapping("/change")
    public void changeNotification(@RequestBody NotificationDto notificationDto) {
        Notification notification = notificationMapper.mapToEntity(notificationDto);
        notificationService.changeNotification(notification);
    }

}
