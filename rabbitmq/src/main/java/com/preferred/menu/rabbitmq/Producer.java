package com.preferred.menu.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class Producer {
    /**

     * AmqpTemplate可以说是RabbitTemplate父类，RabbitTemplate实现类RabbitOperations接口，RabbitOperations继承了AmqpTemplate接口

     */

    @Autowired

    private AmqpTemplate rabbitTemplate;

    @Autowired

    private RabbitTemplate rabbitTemplate1;

    /**

     * 用于单生产者-》单消费者测试

     */

    public void send() {

        String sendMsg = "hello1 " + new Date();

        System.out.println("Sender1 : " + sendMsg);

        for (;;) {
            try {
                this.rabbitTemplate1.convertAndSend("directExchange", "routeKey", sendMsg);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
