package com.manhtb.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
@RequestMapping(value = "/api/v1/notifications")
public class NotificationController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping
    public String get() {
        String url = "http://localhost:9001/api/v1/products";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<String> book = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
        return book.getBody();
    }
}
