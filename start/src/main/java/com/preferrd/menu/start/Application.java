package com.preferrd.menu.start;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import com.preferrd.menu.redis.ConfigProperties;
import com.preferrd.menu.start.exception.MyControllerAdvice;
import com.preferred.menu.rabbitmq.Producer;
import com.preferred.menu.vertx.VerticleHttp;
import com.preferred.menu.vertx.VerticleHttp2;
import com.preferred.menu.websocket.config.WebSocketServer;
import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.vertx.core.Vertx;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@ComponentScan(value = {"com.preferrd.menu.account.service.*", "com.preferrd.menu.account.handler", "com.preferrd.menu.database.*", "com.preferrd.menu.email.*",
        "com.preferrd.menu.redis", "com.preferred.menu.rabbitmq", "com.preferrd.menu.aop.log.*", "com.preferrd.menu.security.configration"
//        ,"com.preferrd.menu.admin"
        , "com.preferred.menu.websocket.*"
        , "com.preferrd.menu.zookeeper.*"
        , "com.preferred.menu.vertx"
}, basePackageClasses = {MyControllerAdvice.class})
@MapperScan("com.preferrd.menu.database.dao")
@ImportResource(value = {"classpath:dubbo-provider.xml"})
@EnableCaching
@EnableAdminServer
@EnableConfigurationProperties(ConfigProperties.class)
public class Application {
    private static Logger LOG = LoggerFactory.getLogger(Application.class);

    @Autowired
    private ApplicationContext applicationCtx;
    @Autowired
    private Producer producer;
    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @Autowired
    private VerticleHttp2 verticleHttp2;
    @Autowired
    private VerticleHttp verticleHttp;
    @Autowired
    @Qualifier(value = "singleVertx")
    private Vertx vertx;
//    @Autowired
//    @Qualifier(value = "clusterVertx")
//    private Vertx vertx2;

    @EventListener
    public void eventListener(ApplicationReadyEvent event) {
        applicationCtx.publishEvent(new myEvent("sss"));
        LOG.error("############################### init: " + event.toString());
    }

    @EventListener
    public void eventListen(myEvent s) {
        LOG.error("############################### String: " + s);
    }

    @Value("${test.name}")
    private String str;

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        springApplication.setAddCommandLineProperties(false);
        springApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            LOG.info("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                if (beanName.equalsIgnoreCase("ConfigProperties")) {
                    LOG.info(beanName);
                }
            }
            LOG.warn("configProperties Bean: " + ((ConfigProperties) applicationCtx.getBean("configProperties")).getHostName());
            producer.send();
            HttpGet httpGet = new HttpGet("http://localhost:8088/test/test2");
            //admin1:admin1
            httpGet.setHeader("Authorization", "Basic YWRtaW46YWRtaW4=");
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            LOG.info("1. " + String.valueOf(response.getStatusLine()));

            HttpGet httpGet2 = new HttpGet("http://localhost:8088/test/test2");
            //admin2:admin2
            httpGet.setHeader("Authorization", "Basic YWRtaW46YWRtaW4=");
            CloseableHttpClient httpClient2 = HttpClientBuilder.create().build();
            CloseableHttpResponse response2 = httpClient2.execute(httpGet2);
            LOG.info("2. " + String.valueOf(response2.getStatusLine()));

            try {
                RestTemplate restTemplate = restTemplateBuilder.basicAuthentication("admin", "admin").build();
                ResponseEntity<String> result = restTemplate.getForEntity("http://localhost:8088/test/test", String.class);
                LOG.info("3. " + result.getHeaders() + "\n" + result.getStatusCode() + "\n" + result.getBody());
            } catch (Exception e) {
                LOG.error("Exception : ", e);
            }

            vertx.deployVerticle(verticleHttp2);
//            vertx.deployVerticle(verticleHttp);

            new Thread(() -> {
                LOG.info("开始websocket发送");
                for (int i = 0; ; i++) {
                    try {
                        TimeUnit.MINUTES.sleep(10);
                        WebSocketServer.sendInfo("new Message for websocket", String.valueOf(i));
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        };
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
        };
    }

    public static class myEvent extends ApplicationEvent {

        myEvent(Object source) {
            super(source);
        }
    }

}
