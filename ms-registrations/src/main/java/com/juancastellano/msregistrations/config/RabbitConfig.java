package com.juancastellano.msregistrations.config;

import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitConfig {
    private final CachingConnectionFactory cachingConnectionFactory;

    public RabbitConfig(CachingConnectionFactory cachingConnectionFactory) {
        this.cachingConnectionFactory = cachingConnectionFactory;
    }

    @Bean
    public Queue deletedStudentQueue() {
        return new Queue("deleted-students-queue", true);
    }

    @Bean
    public Queue deletedCourseQueue() {
        return new Queue("deleted-courses-queue", true);
    }

    @Bean
    public DirectExchange studentExchange() {
        return new DirectExchange("studentExchange");
    }

    @Bean
    public DirectExchange courseExchange() {
        return new DirectExchange("courseExchange");
    }

    @Bean
    public Binding bindingDeletedStudentQueue(Queue deletedStudentQueue, DirectExchange studentExchange) {
        return BindingBuilder.bind(deletedStudentQueue).to(studentExchange).with("deleted-students-queue");
    }

    @Bean
    public Binding bindingDeletedCourseQueue(Queue deletedCourseQueue, DirectExchange courseExchange) {
        return BindingBuilder.bind(deletedCourseQueue).to(courseExchange).with("deleted-courses-queue");
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(cachingConnectionFactory);
        return template;
    }
}
