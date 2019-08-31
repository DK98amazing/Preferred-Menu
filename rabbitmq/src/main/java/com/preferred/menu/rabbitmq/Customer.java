package com.preferred.menu.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Envelope;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RabbitListener(queues = "helloQueue")
public class Customer {
    @RabbitHandler
    public void process(String hello, Channel channel, Message message) {
        System.out.println(message.getMessageProperties());
        System.out.println("Receiver1  : " + hello);
        try {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
