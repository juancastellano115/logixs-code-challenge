package com.juancastellano.msstudents.rabbitmq;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageWithID(String message, String studentID) {
        // Agregar el ID al contenido del mensaje
        String messageWithID = message + studentID;
        rabbitTemplate.convertAndSend("studentExchange", "deleted-students-queue", messageWithID);
    }
}