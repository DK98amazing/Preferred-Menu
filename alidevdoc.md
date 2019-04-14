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

11. 表必备三字段：id, gmt_create, gmt_modified

12. 