package com.example.accessingdatamysql;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



@Component
public class RabbitMQConsumer {
//    @Bean
//    public Jackson2JsonMessageConverter converter() {
//        return new Jackson2JsonMessageConverter();
//    }

    @Autowired
    // Which is auto-generated by Spring, we will use it to handle the data
    private ProductRepository productRepository;

    @RabbitListener(queues = "${spring.rabbitmq.queue}", replyContentType = "application/json")
    public void receivedMessage(String message) throws JsonProcessingException {
        System.out.println("Через RabbitMQ получен новый продукт: " + " " + message);
        ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
        Product database = mapper.readValue(message, Product.class);
        productRepository.save(database);
    }
}
