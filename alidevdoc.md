### notes
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
	
	
	
