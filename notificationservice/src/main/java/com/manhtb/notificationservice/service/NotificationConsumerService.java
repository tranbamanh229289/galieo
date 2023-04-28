package com.manhtb.notificationservice.service;

import com.manhtb.notificationservice.event.OrderEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class NotificationConsumerService {
    Logger logger = LoggerFactory.getLogger(NotificationConsumerService.class);
    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumer(OrderEvent event) {
        logger.info(String.format(event.toString()));

    }
}
