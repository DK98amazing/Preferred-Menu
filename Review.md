## ali_java_doc notes
1. mpleDataFormat存在线程问题，jdk8建议：Instant 代替 Date，LocalDateTime 代替 Calendar，DateTimeFormatter 代替 SimpleDateFormat

2. ScheduledExecutorService容错高于Timer

3. 推荐使用CountDownLatch异步转同步

4. 并发场景下 单例模式最好使用双检查

```
class Singleton{
	private Singleton s = null;
	Singleton(){
		if(s==null)synchronized(this){
			if(s==null){
				s = new Singleton();
			}
		}
		return s;
	}
}
```

5. jdk8建议 LongAdder替换AtomicLong，性能更好

6. ThreadLocal定义为静态，解决线程内共享问题

7. ThreadLocalRandom

8. ThreadPoolExecutor

9. if-else  最好别超过三层。卫语句、策略模式、状态模式

10. 方法添加参数校验的原则

	11. 为具有唯一特性的字段创建索引是必须的

	12. 避免多表的join操作，join字段必须类型一致、

	13. sql中使用isNUll 比较null值 ，原理 数据库中的所有null认为不一样并且 null之间的计算结构返回任然为null
	14. count 和 sum方法的计算结果不一样的
	15. 存储过程调试困难 扩展性差 没有移植性
	16.删除和修改数据前，应该先查询，避免误操作
	17.truncate table 应该避免，该语句无事务并且不触发触发器，
	
## SQL
###basic
	“Where” 是一个约束声明，使用Where来约束来之数据库的数据，Where是在结果返回之前起作用的，且Where中不能使用聚合函数。

	“Having”是一个过滤声明，是在查询返回结果集以后对查询结果进行的过滤操作，在Having中可以使用聚合函数。

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
		
		
		
		
		
		
		
