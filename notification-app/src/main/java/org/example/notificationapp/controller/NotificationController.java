package org.example.notificationapp.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.notificationapp.dto.NotificationDto;
import org.example.notificationapp.entity.Notification;
import org.example.notificationapp.mapper.NotificationMapper;
import org.example.notificationapp.service.NotificationService;
import org.example.notificationapp.service.TelegramService;
import org.example.notificationapp.validation.RequestValidation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;

import java.nio.file.Files;
import java.nio.file.Path;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/notification")
public class NotificationController {

    private final NotificationService notificationService;
    private final TelegramService telegramService;
    private final NotificationMapper notificationMapper;

    private final RequestValidation requestValidation;

    @PostMapping("/change")
    public ResponseEntity<Object> changeNotification(@Valid @RequestBody NotificationDto notificationDto, BindingResult bindingResult) {
        ResponseEntity<Object> errors = requestValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;

        Notification notification = notificationMapper.mapToEntity(notificationDto);
        notificationService.changeNotification(notification);
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }

    @PostMapping("/change-enable/{channel}")
    public ResponseEntity<Object> changeNotificationEnable(@PathVariable("channel") String channel,
                                                           @RequestParam("parameter") String parameter,
                                                           @RequestParam("enable") Boolean enable) {
        notificationService.changeNotificationEnable(channel, parameter, enable);
        return new ResponseEntity<>("Уведомления успешно поменяли статус", HttpStatus.OK);
    }

    @DeleteMapping("/delete/{channel}")
    public ResponseEntity<Object> deleteNotification(@PathVariable("channel") String channel,
                                                     @RequestParam("parameter") String parameter) {
        notificationService.deleteChannel(channel, parameter);
        return new ResponseEntity<>("Уведомление по текущему каналу успешно удалено", HttpStatus.OK);
    }

    @PostMapping("/upload/{channel}")
    public ResponseEntity<Object> uploadFileBot(
            @RequestParam("file") MultipartFile multipartFile,
            @RequestParam("parameter") String parameter,
            @PathVariable("channel") String channel
    ) {
        try {
            Path tempPath = Files.createTempFile("export-file-", ".pdf");
            multipartFile.transferTo(tempPath);

            if (channel.equals("bot")) {
                SendDocument fileForBot = notificationService.getFileForBot(parameter, tempPath.toFile());
                telegramService.sendFile(fileForBot);
            }
            else if (channel.equals("mail"))
                notificationService.sendFileMail(parameter, tempPath.toFile());

            Files.deleteIfExists(tempPath);

            log.info("Файл успешно отправлен");
            return new ResponseEntity<>("Файл успешно отправлен", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Проблема с отправкой файла. Error: " + e.getMessage(),
                    HttpStatus.BAD_REQUEST);
        }
    }

}
