package com.alertcare.server.openai.service;

import com.alertcare.server.openai.dto.Message;
import com.alertcare.server.openai.dto.OpenAiRequest;
import com.alertcare.server.openai.dto.OpenAiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class OpenAiService {

    private final RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.url}")
    private String url;

    @Value(("${openai.model}"))
    private String model;

    public String callGpt(String prompt) {
        OpenAiRequest request = new OpenAiRequest(model, prompt);
        OpenAiResponse response = restTemplate.postForObject(url, request, OpenAiResponse.class);

        return response.getChoices().get(0).getMessage().getContent();
    }
}
