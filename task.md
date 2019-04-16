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

## 数据库部署和调优		2d
###basic
	“Where” 是一个约束声明，使用Where来约束来之数据库的数据，Where是在结果返回之前起作用的，且Where中不能使用聚合函数。

	“Having”是一个过滤声明，是在查询返回结果集以后对查询结果进行的过滤操作，在Having中可以使用聚合函数。
	
	查询时列之间可以直接做算术运算
	
	常见谓语句：
	

###事务

###锁

###索引
	聚集索引：
	非聚集索引：
###连接查询
	内连接：相等连接：using（column）；自然连接： natural join；交叉连接：cross join；
	外链接：左外 left outer join；右外：right outer join；全连接：full outer join；
###优化
	以下均针对mySql数据库
####软优化
	查询语句优化：
		使用explain 分析查询
	查询子句优化：尽量使用join 替代子查询，因为子查询会创建临时表。
	索引：使用索引优化查询，使用索引时注意 like or 左匹配等原则，避免索引失效。
	拆表：表字段过多时，将使用率低的表单独拆出
	中间表：如果某些业务需要进行大量连接查询，建议为这些数据创建中间表，减少连接查询
	适当冗余：和中间表一样，操作也类似，目的是为了减少连接查询
	分析表：使用analyze 分析表中的关键字分布？？？ analyze如何使用
	检查表：检查表中是否存在错误？？？ check关键字？？？
	优化表：消除因为删除或者更新操作造成的空间浪费 optimize关键字？？？


####硬优化
	硬件资源：加cpu  加内存 
	数据库参数配置：在my.cnf或者my.ini中
		key_buffer_size : 索引缓冲区大小
		table_cache:可以同时打开的表个数？？？
		query_cache_size:查询缓冲区大小，query_cache_type查询缓冲器开关：0 关，1 开 ， 2 查询指定时使用
		sort_buffer_size：排序缓冲区大小。
		更多的参数配置参见：https://www.mysql.com/cn/why-mysql/performance/index.html
	分库分表：
		分库，分表	根据业务分库，根据表大小（字段个数，行数）分表
		主从分离，各自承担读写操作。

		数据库本身无法承担高并发请求，一般的单机并发量在每秒几千次，但数据库的机器一般较贵，也不适合不断加机器解决。
		因此可以引入缓存机制，缓存的单机并发量可达几万，几十万


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

## actor /  reactor 模型

## 面试说辞			2d
	自我介绍
	项目介绍

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


