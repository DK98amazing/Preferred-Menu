# Preferred-Menu
菜单优选系统，全方位维护您的饮食营养健康。解决每餐点餐时无法决定吃什么的问题。

##个人模式下：
- 数据源：个人记录自己平常能吃到的东西的基本信息（位置，店铺（用户提供），价格，排队时间，菜种，菜名，口味）。
- 服务：等到用餐时间，系统根据用户的健康信息，季节信息，用餐时间为用户推荐合适的餐饭。原则上要求兼顾用户一个周期内的的经济，健康，口味，等条件的平衡。
- 独立的系统：（用户管理。菜品管理。推荐系统。）

###技术架构：
1. 平台：小程序。
2. 后台：由于响应式编程的特性，Spring WebFlux和Reactor底层需要支持异步的运行环境，比如Netty和Undertow；也可以运行在支持异步I/O的
       Servlet 3.1的容器之上，比如Tomcat（8.0.23及以上）和Jetty（9.0.4及以上）。
```
最终选择Netty最后运行环境，netty具备处理基本http协议的能力，但需要自己实现web相关的功能。选择原因，理解netty。
```
3. 框架：spring boot架构。#分布式系统。微服务架构。Rpc通信（dubbo，grpc）。消息中间件（kafka，redis）。
4. 微服务架构：spring cloud：
```
决定使用dubbo作为rpc框架，考虑下dubbo通过配置代码侵入性底
kafka：hl
redis:lgy
```
5. 前后端通信：restful，websocket。
6. 数据库：postgresql。映射使用MyBatis。
7. 缓存：Redis，mongoDB，（分布式缓存怎么用）

###task:
1. 


##互联模式下：
- 数据源：商家上传店铺信息（名字，位置，大小，客流等）可提供的菜品（价格，排队，优惠，菜种，口味，数量，时间）。
- 服务：等到用餐时间，系统根据用户的健康信息，季节信息，用餐时间为用户推荐合适的餐饭。原则上要求兼顾用户一个周期内的的经济，健康，口
       味，等条件的平衡。可以对店家上传的菜品信息作出评论（如何防止刷评论，信息认证后可以参与评论）。
- 详细需求：
  1. 菜单推荐：
      根据用户的基本信息（年龄，工作，性别，生理状态），季节（春夏，节假日），地理（常住地，其他地点），历史用餐习惯（消费能力，口味，爱好，用餐时间）计算用户适合的用餐内容。
  2. 菜单管理
  3. 为菜单定义名称，
  4. 用户管理：
  5. 店铺管理：
  
- 参考资料
  1. dubbo与spring cloud：
       https://www.cnblogs.com/xishuai/archive/2018/04/13/dubbo-and-spring-cloud.html
  2. wechat docment
       https://developers.weixin.qq.com/miniprogram/dev/devtools/devtools.html?t=19030421
  3. netty实现servlet容器
       https://www.zhihu.com/question/31937124/answer/406295371
  4. 申请小程序账户
       https://baijiahao.baidu.com/s?id=1612907296930000897&wfr=spider&for=pc
  5. kafka
       http://www.cnblogs.com/likehua/p/3999538.html
  6. spring boot 中使用netty做web服务器
  	   https://blog.csdn.net/xx753277/article/details/84935718
  7. spring boot响应式学习资料
  		https://www.cnblogs.com/xwjie/p/8995150.html
  		http://www.cnblogs.com/skyme/p/8976866.html
  		https://www.bysocket.com/archives/1987/1987
  8. 微博的微服务治理框架：Motan
  		https://github.com/weibocom/motan/wiki	
  		https://demoso.net/taview/49891
  9. 阿里Java开发手册
  		https://files-cdn.cnblogs.com/files/han-1034683568/%E9%98%BF%E9%87%8C%E5%B7%B4%E5%B7%B4Java%E5%BC%80%E5%8F%91%E6%89%8B%E5%86%8C%E7%BB%88%E6%9E%81%E7%89%88v1.3.0.pdf
  10. 求职套路  https://blog.csdn.net/tzs_1041218129/article/details/78024225
  11。 AIO BIO BIO demo https://blog.csdn.net/anxpp/article/details/51512200
  12. Uber的kafka应用版本 ：https://github.com/uber/chaperone.git
  13. springboot demo All : https://github.com/wuyouzhuguli/SpringAll.git
  
  
  