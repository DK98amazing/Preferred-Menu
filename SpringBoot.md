# Spring Boot Notes
springboot 2.0   spring5  异步非阻塞IO的响应式流，非阻塞函数式编程 
 
##spring5
基准版本：jdk8，j2ee7

###注解
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

@EnableAutoConfiguration 配置自动加载的规则

@ComponentScan：约束需要扫描的路径，使用@SpringBootApplication等价于@Configuration、@EnableAutoConfiguration 、 @ComponentScan的合并作用

@ConfigurationProperties读取配置文件，与@Value一样都可以绑定数据到对象，但是二者存在区别。需要注意区别。

###关键词
spring data ： 为数据访问提供熟悉且一致的基于Spring的编程模型，同时仍保留底层数据存储的特殊特性。

热部署：spring-boot-devtools:热部署实现原理，两个类加载器

远程调试：spring-boot-maven-plugin

自定义banner，（佛祖保佑，没有BUG，永不宕机），禁止显示banner
SpringApplication app = new SpringApplication(MySpringConfiguration.class);
app.setBannerMode(Banner.Mode.OFF);

ApplicationListener：监听器，用于在初始化容器后完成一些操作。

自定义SpringApplication：自定义应用 NO!

流式API：使用SpringApplicationBuilder配置应用 NO!

事件监听：ApplicationStartingEvent ApplicationEnvironmentPreparedEvent ApplicationPreparedEvent ApplicationReadyEvent ApplicationFailedEvent NO!

在容器启动后运行一些特定的代码：ApplicationRunner,CommandLineRunner,Ordered接口   NO!

容器退出 ExitCodeGenerator SpringApplication.exit()

SBA Spring Boot Admin

spring参数配置，支持多种参数配置方式，不同的配置方式的优先级进行加载。

使用YAML替代Properties

日志配置

web应用的开发：spring-mvc

###笔记
1. spring boot的模块遵循以下命名规则：spring-boot-starter-*

2. SpringApplication会尝试根据依赖自动生成不同的context，在有springmvc时会生成AnnotationConfigServletWebServerApplicationContext，
在存Spring WebFlux但不存在spingmvc时，生成AnnotationConfigReactiveWebApplicationContext，其他默认情况下都是用：AnnotationConfigApplicationContext，
在同样可以使用setApplicationContextClass(…)方法自定义context类型。

3. 可以使用ApplicationArguments 访问SpringApplication.run(...)中传递的参数。示例：

```
@Component
public class MyBean {
    @Autowired
    public MyBean(ApplicationArguments args) {
        boolean debug = args.containsOption("debug");
        List<String> files = args.getNonOptionArgs();
        // if run with "--debug logfile.txt" debug=true, files=["logfile.txt"]
    }
}
```

4.容器退出：ExitCodeGenerator接口：提供退出时执行的特殊代码, SpringApplication.exit()退出方法，可以将此方法作为参数传递给System.exit()

```
@SpringBootApplication
public class ExitCodeApplication {
    @Bean
    public ExitCodeGenerator exitCodeGenerator() {
        return () -> 42;
    }
    public static void main(String[] args) {
        System.exit(SpringApplication
                .exit(SpringApplication.run(ExitCodeApplication.class, args)));
    }
}
```

5.Spring Boot Admin:用于监控spring应用的内存和运行状况。
开原地址：https://github.com/codecentric/spring-boot-admin

6. application.setDefaultProperties设置默认属性（优先级最低）  配置文件中的属性占位符会修改manven占位符吧的规则（${属性}--》@属性@）

7.日志配置：
	logging.level.root=WARN
	logging.level.org.springframework.web=DEBUG
	logging.level.org.hibernate=ERROR
	
8.HttpMessageConverters：实现请求和响应的转换，可以自定义覆盖原有的默认配置。

```
import org.springframework.boot.autoconfigure.web.HttpMessageConverters;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.*;
@Configuration
public class MyConfiguration {
    @Bean
    public HttpMessageConverters customConverters() {
        HttpMessageConverter<?> additional = ...
        HttpMessageConverter<?> another = ...
        return new HttpMessageConverters(additional, another);
    }
}
```

MessageCodesResolver : 用于从绑定的errors产生用来渲染错误信息的错误码  ???

静态资源：默认情况下，Spring Boot从classpath下的/static（/public，/resources或/META-INF/resources）文件夹，或从ServletContext根目录提供静态内容这是通过Spring MVC的ResourceHttpRequestHandler实现的。



###图



























