# Spring Boot Notes
springboot 2.0   spring5  异步非阻塞IO的响应式流，非阻塞函数式编程 
 
##spring5
基准版本：jdk8，j2ee7

###常用注解
@RsetController = @Controller + @ResponseBody 

@Configuration用于定义配置类，类中应该包含一个或多个被@Bean修饰的方法。被修饰的不能是final，匿名类。

@Bean标注在方法上(返回某个实例的方法) ，用于注册bean实例，@Bean(name="testBean",initMethod="start",destroyMethod="cleanUp")。默认的是scope是singleton，可以使用@Scope重新定义

@ImportResource和@Value加载资源文件和属性读取

```
@Configuration
@ImportResource("classpath:/com/cloud/skyme/properties-config.xml")
public class AppConfig{
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Bean
    public DataSource dataSource() {
        return new DriverManagerDataSource(url,username,password);
    }
}
```

###关键词
spring data ： 为数据访问提供熟悉且一致的基于Spring的编程模型，同时仍保留底层数据存储的特殊特性。


###笔记
1. spring boot的模块遵循以下命名规则：spring-boot-starter-*

###图


