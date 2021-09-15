package com.example.accessingdatamysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQConsumer {

    @Autowired
    private ProductRepository productRepository;

    @RabbitListener(queues = "${spring.rabbitmq.queue}", replyContentType = "application/json")
    public void receivedMessage(String message) throws JsonProcessingException {
        System.out.println("Через RabbitMQ получен новый продукт: " + " " + message);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Product database = mapper.readValue(message, Product.class);
        productRepository.save(database);
    }
}
