package com.preferrd.menu.start;

//import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;

import com.preferrd.menu.redis.ConfigProperties;
import com.preferred.menu.rabbitmq.Producer;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.server.ConfigurableWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Arrays;

@SpringBootApplication
@ComponentScan({"com.preferrd.menu.account.service.*", "com.preferrd.menu.account.handler", "com.preferrd.menu.database.*", "com.preferrd.menu.email.*",
        "com.preferrd.menu.redis", "com.preferred.menu.rabbitmq", "com.preferrd.menu.aop.log.*", "com.preferrd.menu.security.configration"})
@MapperScan("com.preferrd.menu.database.dao")
@ImportResource(value = {"classpath:dubbo-provider.xml"})
@EnableCaching
@EnableConfigurationProperties(ConfigProperties.class)
public class ApplicationNginxTest {

    @Autowired
    private ApplicationContext applicationCtx;
    @Autowired
    private Producer producer;

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

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                if (beanName.equalsIgnoreCase("ConfigProperties")) {
                    System.out.println(beanName);
                }
            }
            System.err.println("********" + ((ConfigProperties) applicationCtx.getBean("configProperties")).getHostName());
            System.err.println("********" + str);
            producer.send();
            HttpGet httpGet = new HttpGet("http://localhost:8070/test/test2");
            //admin1:admin1
            httpGet.setHeader("Authorization", "Basic YWRtaW4xOmFkbWluMQ==");
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            CloseableHttpResponse response = httpClient.execute(httpGet);
            System.err.println(response.getStatusLine());

            HttpGet httpGet2 = new HttpGet("http://localhost:8070/test/test2");
            //admin2:admin2
            httpGet.setHeader("Authorization", "Basic YWRtaW4yOmFkbWluMg==");
            CloseableHttpClient httpClient2 = HttpClientBuilder.create().build();
            CloseableHttpResponse response2 = httpClient2.execute(httpGet2);
            System.err.println(response2.getStatusLine());
        };
    }

    @Bean
    public WebServerFactoryCustomizer<ConfigurableWebServerFactory> webServerFactoryCustomizer() {
        return factory -> {
        };
    }

}
