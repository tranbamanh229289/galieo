package com.manhtb.orderservice.event;

import com.manhtb.orderservice.dto.OrderDto;

public class OrderEvent {
    private String message;
    private String status;
    private OrderDto orderDto;
}
