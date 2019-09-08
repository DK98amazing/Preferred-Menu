package com.preferrd.menu.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ClusterOperations;
import org.springframework.data.redis.core.GeoOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis();
        jedis.select(5);

        System.out.println(jedis.getDB());
        jedis.flushDB();
        jedis.set("testString", "testStringValue");
        jedis.setex("testString60", 60, "testValue60");
        jedis.setnx("testString", "testStringValue");
        jedis.mset("testStringm1", "testStringValuem1", "testStringm2", "testStringValuem2");

        jedis.lpush("collections", "ArrayList", "Vector", "Stack", "HashMap", "WeakHashMap", "LinkedHashMap");
        jedis.lpush("collections", "HashSet");
        jedis.lpush("collections", "TreeSet");
        jedis.lpush("collections", "TreeMap");
        jedis.rpush("collections", "collec");
        jedis.lrem("collections", 2, "ArrayList");
        jedis.lpop("collections");
        System.out.println(jedis.lrange("collections", 0, -1));

        jedis.sadd("eleSet", "e1","e2","e4","e3","e0","e8","e7","e5");
        jedis.spop("eleSet");
        jedis.srem("eleSet", "e0");
        jedis.smembers("eleSet");

        Map<String,String> map = new HashMap<String,String>();
        map.put("key1","value1");
        map.put("key2","value2");
        map.put("key3","value3");
        map.put("key4","value4");
        jedis.hmset("hash",map);
        jedis.hset("hash:hash1", "key5", "value5");
        jedis.hincrBy("hash", "key6", 3);
        jedis.hget("hash", "key6");

        Map<String,Double> map2 = new HashMap<>();
        map2.put("key2",1.2);
        map2.put("key3",4.0);
        map2.put("key4",5.0);
        map2.put("key5",0.2);
        System.out.println(jedis.zadd("zset", 3,"key1"));
        System.out.println(jedis.zadd("zset",map2));

        jedis.lpush("userlist", "33");
        jedis.lpush("userlist", "22");
        jedis.lpush("userlist", "55");
        jedis.lpush("userlist", "11");
        jedis.hset("user:66", "name", "66");
        jedis.hset("user:55", "name", "55");
        jedis.hset("user:33", "name", "33");
        jedis.hset("user:22", "name", "79");
        jedis.hset("user:11", "name", "24");
        jedis.hset("user:11", "add", "beijing");
        jedis.hset("user:22", "add", "shanghai");
        jedis.hset("user:33", "add", "guangzhou");
        jedis.hset("user:55", "add", "chongqing");
        jedis.hset("user:66", "add", "xi'an");
        SortingParams sortingParameters = new SortingParams();
        sortingParameters = new SortingParams();
        sortingParameters.get("user:*->name");
        sortingParameters.get("user:*->add");
        System.out.println(jedis.sort("userlist",sortingParameters));


    }
}
