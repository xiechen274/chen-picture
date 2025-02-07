package com.chen.picturebackend.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ai")
public class OllamaTestController {

    private final RestTemplate restTemplate = new RestTemplate();
    private static final String OLLAMA_URL = "http://localhost:11434/api/generate"; // Ollama 服务器地址

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("model", "mistral"); // 选择 Ollama 模型，例如 "mistral", "gemma", "llama2"
        requestBody.put("prompt", message);

        ResponseEntity<String> response = restTemplate.postForEntity(OLLAMA_URL, requestBody, String.class);
        return response.getBody();
    }
}

