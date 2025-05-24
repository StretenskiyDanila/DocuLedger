package org.example.businesspack.services;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.example.businesspack.logs.LogMessage;
import org.example.businesspack.metrics.JavaFxMetrics;
import org.example.businesspack.request.NotificationDto;

import java.io.ByteArrayOutputStream;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Slf4j
public class NotificationClient {

    private final JavaFxMetrics metrics = new JavaFxMetrics();
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final String BASE_URL = "http://localhost:8081/api/v1/notification";

    public void changeNotification(NotificationDto dto) {
        log.info(LogMessage.START_METHOD.getMessage(), "changeNotification()");
        String jsonBody = convertDtoToJson(dto);
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL + "/change"))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(jsonBody))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                System.out.println("Successfully changed: " + response.body());
            } else {
                System.out.println("Ошибка: " + response.body());
            }
        } catch (Exception e) {
            log.error("Error during notification change: {}", e.getMessage());
        }
    }

    public void changeNotificationEnable(String channel, String parameter, Boolean enable) throws Exception {
        log.info(LogMessage.START_METHOD.getMessage(), "changeNotificationEnable()");
        String encodedParameter = URLEncoder.encode(parameter, StandardCharsets.UTF_8);
        String url = String.format("%s/change-enable/%s?parameter=%s,enable=%s",
                BASE_URL, channel, encodedParameter, enable);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println(response.body());
        } else {
            System.out.println("Ошибка: " + response.body());
        }
    }

    public void deleteNotification(String channel, String parameter) throws Exception {
        log.info(LogMessage.START_METHOD.getMessage(), "deleteNotification()");
        String encodedParameter = URLEncoder.encode(parameter, StandardCharsets.UTF_8);
        String url = String.format("%s/delete/%s?parameter=%s",
                BASE_URL, channel, encodedParameter);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .DELETE()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            System.out.println("Успешно удалено: " + response.body());
        } else {
            System.out.println("Ошибка: " + response.body());
        }
    }

    public void uploadFile(String channel, String parameter, Path filePath) throws Exception {
        log.info(LogMessage.START_METHOD.getMessage(), "uploadFile()");
        long start = System.currentTimeMillis();
        String boundary = "Boundary-" + UUID.randomUUID();

        byte[] fileBytes = Files.readAllBytes(filePath);
        String fileName = filePath.getFileName().toString();

        ByteArrayOutputStream body = new ByteArrayOutputStream();

        // Добавляем файл
        String filePart = "--" + boundary + "\r\n" +
                "Content-Disposition: form-data; name=\"file\"; filename=\"" + fileName + "\"\r\n" +
                "Content-Type: application/octet-stream\r\n\r\n";
        body.write(filePart.getBytes());
        body.write(fileBytes);
        body.write("\r\n".getBytes());

        String paramPart = "--" + boundary + "\r\n" +
                "Content-Disposition: form-data; name=\"parameter\"\r\n\r\n" +
                parameter + "\r\n";
        body.write(paramPart.getBytes());

        body.write(("--" + boundary + "--\r\n").getBytes());

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(BASE_URL + "/upload/" + channel))
                .header("Content-Type", "multipart/form-data; boundary=" + boundary)
                .POST(HttpRequest.BodyPublishers.ofByteArray(body.toByteArray()))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            metrics.trackOperation("document.save.time", System.currentTimeMillis() - start);
            metrics.pushMetrics();
            System.out.println("Файл успешно отправлен: " + response.body());
        } else {
            System.out.println("Ошибка отправки: " + response.body());
        }
    }

    private String convertDtoToJson(NotificationDto dto) {
        Gson gson = new Gson();
        return gson.toJson(dto);
    }

}
