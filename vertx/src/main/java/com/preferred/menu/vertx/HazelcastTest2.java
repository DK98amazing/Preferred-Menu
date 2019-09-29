package com.preferred.menu.vertx;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.ITopic;
import com.hazelcast.core.Message;
import com.hazelcast.core.MessageListener;

public class HazelcastTest2 implements MessageListener<String> {

    static HazelcastInstance h;

    public static void main(String[] args) {
        Config config = new Config();
        h = Hazelcast.newHazelcastInstance(config);
        ITopic<String> topic = h.getTopic("my-distributed-topic");
        topic.addMessageListener(new HazelcastTest2());
        topic.publish("Hello to distributed world---");
    }

    @Override
    public void onMessage(Message<String> message) {
        System.out.println("Got message " + message.getMessageObject());

//        h.shutdown();
    }
}
