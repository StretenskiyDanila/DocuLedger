package org.example.businesspack.services;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.example.businesspack.request.NotificationDto;
import org.example.businesspack.request.enums.ChannelNotification;
import org.example.businesspack.request.enums.TabState;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NotificationClientTest {

    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URL = "http://localhost:8081/api/v1/notification";

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("provideCorrectNotificationDto")
    void test_changeNotification_withCorrectDto(NotificationDto dto) {
        String jsonBody = convertDtoToJson(dto);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/change"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        System.out.println(response.body());
    }

    @Test
    void changeNotificationEnable() {
    }

    @SneakyThrows
    @ParameterizedTest
    @MethodSource("provideDeleteNotificationDto")
    void test_deleteNotification(String channel, String parameter) {
        String encodedParameter = URLEncoder.encode(parameter, StandardCharsets.UTF_8);
        String url = String.format("%s/delete/%s?parameter=%s",
                BASE_URL, channel, encodedParameter);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
    }

    @Test
    void uploadFile() {
    }

    private String convertDtoToJson(NotificationDto dto) {
        Gson gson = new Gson();
        return gson.toJson(dto);
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

    private static Stream<Arguments> provideDeleteNotificationDto() {
        return Stream.of(
                Arguments.of(
                "telegram", "stretenskiy_danila",
                "mail", "stretenskiy_danila@mail.ru"));
    }

}