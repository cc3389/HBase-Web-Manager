package edu.wit.hbasemanager.rabbitmqservice.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class MQConfig {
    @Bean
    public RabbitTemplate rabbitTempLate(CachingConnectionFactory connectionFactory) {
        connectionFactory.setPublisherConfirmType(CachingConnectionFactory.ConfirmType.CORRELATED);
        connectionFactory.setPublisherReturns(true);
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) ->
                log.info("消息发送成功:correlationData({}),ack({}),cause({})", correlationData, ack, cause));
        rabbitTemplate.setReturnsCallback(returnedMessage
                -> log.info("消息丢失:exchange({}),route({}),replyCode({}),replyText({}),message:{}",
                returnedMessage.getExchange(), returnedMessage.getRoutingKey(),
                returnedMessage.getReplyCode(), returnedMessage.getReplyText(),
                returnedMessage.getMessage())
        );
        return rabbitTemplate;
    }
    @Bean
    public Queue directQueue() {
        return new Queue("direct.mail");
    }

}
