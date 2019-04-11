IO demo			2d	doing

netty入门		2d		check

netty原理		1d

docker入门		1d	doing

docker原理		1d	

jenkins入门		2d	doing

springBoot入门		1w	doing

springCloud入门		2w

dubbo入门		2d

redis入门+实践  		2d

nginx入门+实践		2d

消息中间件		2d

数据库复习		2d		doing

设计模式复习		2d

jvm调优，数据库调优	2d

基本算法			2d

单元测试			2d

swagger			2d

解决方案：

分布式缓存			2d

分布式锁			2d

单点登录			2d

微服务架构			2d

应用集群			2d

服务器部署和调优		2d

数据库部署和调优		2d

###负载均衡		

	负载均衡器：专门处理负载均衡算法的软件和硬件设备，根据不同的协议通常可以分为
	1传输层实现（tcp层面实现效率高，但是无法保证长连接），2应用层实现。
	流行的有nginx，HAproxy，KeepAlived。linux还自带LVS（Linux virtual server）这是一种传输层实现。
	
	常用策略：
	轮询（round robin）：实现简单，分布均匀。但是后端服务性能常常不均匀。
	加权轮询（weighted round robin）：针对后端服务器性能差异的解决办法，ngnix支持
	随机（random）
	
	以上三种常用于无状态的负载场景中，对于有长连接和命中率要求的场景都不适用。
	
	hash：
	可以解决命中率和长连接问题，但是对后端设备的缩减或者扩容，由于需要重新hash，会导致所有的请求路由同时发生变化，导致命中率短期急剧下降。可以通过主备倒换解决，但是并不是最优方案，因为主备切换存在延迟。
	
	最小连接数LC：
	始终把请求分配给，后端活动连接数最少的一个。需要维护一张服务器的连接状态表
	
	加权最小连接数WLC：
	原理同加权轮询
	
	最短响应时间LRT：
	始终将请求分配给，平均响应时间最短的设备。平均时间通过正常连接逐渐维护。
	
	一致性hash：
	hash环，节点虚拟，环上映射。对扩容和宕机支持友好，影响最少。

actor /  reactor 模型

##work：奥威亚
	webservice
	json
	多线程
	集合
	
	分布式
	缓存
	消息
	搜索
	
	html
	jsp
	servlet		
	
	mysql
	
	JSF				放弃
	strust2		放弃
	spring
	hibernate		放弃
	mybatis
	
	Linux下配置tomacat，niginx，jboss，调优
	
	

