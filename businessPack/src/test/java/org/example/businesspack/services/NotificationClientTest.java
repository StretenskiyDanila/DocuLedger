package org.example.businesspack.services;

import org.example.businesspack.request.NotificationDto;
import org.example.businesspack.request.enums.ChannelNotification;
import org.example.businesspack.request.enums.TabState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.nio.file.Path;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class NotificationClientTest {

    private final NotificationClient client = new NotificationClient();

    @DisplayName("Проверка корректности отправки запроса на изменение уведомлений")
    @ParameterizedTest
    @MethodSource("provideCorrectNotificationDto")
    void test_changeNotification_withCorrectDto(NotificationDto dto) {
        assertDoesNotThrow(() -> client.changeNotification(dto));
    }

    @DisplayName("Проверка корректности отправки запроса на включение/отключение уведомлений")
    @ParameterizedTest
    @MethodSource("provideChangeNotificationDto")
    void changeNotificationEnable(String channel, String parameter, Boolean enable) {
        assertDoesNotThrow(() -> client.changeNotificationEnable(channel, parameter, enable));
    }

    @DisplayName("Проверка корректности отправки запроса на удаление уведомлений")
    @ParameterizedTest
    @MethodSource("provideDeleteNotificationDto")
    void test_deleteNotification(String channel, String parameter) {
        assertDoesNotThrow(() -> client.deleteNotification(channel, parameter));
    }

    @DisplayName("Проверка корректности отправки запроса на загрузку файла")
    @ParameterizedTest
    @MethodSource("provideUploadFileNotificationDto")
    void uploadFile(String channel, String parameter, Path filePath) {
        assertDoesNotThrow(() -> client.uploadFile(channel, parameter, filePath));
    }

    private static NotificationDto[] provideCorrectNotificationDto() {
        return new NotificationDto[]{
                NotificationDto.builder()
                        .state(TabState.RUNNING)
                        .channel(ChannelNotification.TELEGRAM)
                        .userName("stretenskiy_danila")
                        .userMail("stretenskiy_danila@mail.ru")
                        .tabName("account")
                        .enable(true)
                        .build(),
                NotificationDto.builder()
                        .state(TabState.FINISHED)
                        .channel(ChannelNotification.TELEGRAM)
                        .userMail("stretenskiy_danila@mail.ru")
                        .userName("stretenskiy_danila")
                        .tabName("act")
                        .enable(false)
                        .build(),
                NotificationDto.builder()
                        .state(TabState.RUNNING)
                        .channel(ChannelNotification.MAIL)
                        .userName("stretenskiy_danila@mail.ru")
                        .userName("stretenskiy_danila")
                        .tabName("account")
                        .enable(true)
                        .build()};
    }

    private static Stream<Arguments> provideChangeNotificationDto() {
        return Stream.of(
                Arguments.of("telegram", "stretenskiy_danila", true),
                Arguments.of("mail", "stretenskiy_danila@mail.ru", false));
    }

    private static Stream<Arguments> provideDeleteNotificationDto() {
        return Stream.of(
                Arguments.of(
                "telegram", "stretenskiy_danila",
                "mail", "stretenskiy_danila@mail.ru"));
    }

    private static Stream<Arguments> provideUploadFileNotificationDto() {
        return Stream.of(
                Arguments.of("telegram", "stretenskiy_danila", NotificationClientTest.class.getResource("test.pdf")),
                Arguments.of("mail", "stretenskiy_danila@mail.ru", NotificationClientTest.class.getResource("test.pdf")));
    }

}