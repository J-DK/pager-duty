package com.acko.pager.service;

import com.acko.pager.model.NotificationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class NotificationService {

    RestTemplate restTemplate = new RestTemplate();

    String url = "https://run.mocky.io/v3/fd99c100-f88a-4d70-aaf7-393dbbd5d99f";


    public String notify(String phone, String message) {

        Map<String, String> requestMap = new HashMap<>();
        requestMap.put("phone_number", phone);
        requestMap.put("message", message);

        ResponseEntity<NotificationResponse> response = restTemplate.postForEntity(url, requestMap, NotificationResponse.class);

        return response.getBody().getSuccess();
    }
}
