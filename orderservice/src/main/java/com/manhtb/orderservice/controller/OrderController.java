package com.manhtb.orderservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/{bookId}")
    public String getNameBook(@PathVariable Long bookId) {
        String urlProductService = "http://localhost:9001/api/v1/products/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(new HttpHeaders());
        ResponseEntity<String> book=
                restTemplate.exchange(urlProductService + String.valueOf(bookId), HttpMethod.GET, httpEntity, String.class);
        return book.getBody();
    }
}
