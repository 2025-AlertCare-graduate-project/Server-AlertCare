package com.alertcare.server.fcm.service;

import com.alertcare.server.fcm.FcmAccessTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import org.springframework.http.*;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FcmSendService {

    private final FcmAccessTokenProvider tokenProvider;
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${fcm.project-id}")
    private String projectId;

    public void sendMessage(String targetToken, String title, String body) throws IOException {
        String url = "https://fcm.googleapis.com/v1/projects/" + projectId + "/messages:send";

        String accessToken = tokenProvider.getAccessToken();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> message = Map.of(
                "message", Map.of(
                        "token", targetToken,
                        "data", Map.of(
                                "title", title,
                                "body", body
                        ),
                        "android", Map.of(
                                "priority", "high"
                        )
                )
        );

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(message, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        System.out.println("FCM Response: " + response.getBody());
    }
}