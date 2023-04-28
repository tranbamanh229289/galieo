package com.manhtb.orderservice.controller;

import com.manhtb.orderservice.dto.OrderDto;
import com.manhtb.orderservice.event.OrderEvent;
import com.manhtb.orderservice.service.OrderProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderProducerService orderProducerService;

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

    @PostMapping
    public String postMessage(@RequestBody OrderDto orderDto) {
        orderDto.setId(UUID.randomUUID().toString());
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setStatus("PENING");
        orderEvent.setMessage("order is sending");
        orderEvent.setOrderDto(orderDto);
        orderProducerService.sendMessage(orderEvent);
        return "sended";
    }
}
