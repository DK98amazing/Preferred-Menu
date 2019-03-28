##Netty

### key words

NIO

BIO	：	ServerSocket ，Socket

AIO

selector

channel

socket

websocket


### notes

1。 原生NIO存在bug(jdk1.7以前)

2. 相比于原生NIO:解决了已发现的bug，api简洁化，降低学习门槛

3. websocket：使用http通信机制握手建立连接,通信开销，B/S互相通信。
	浏览器向server发送请求建立连接的http请求，该请求的header区别于普通http请求。
	
4. websocket生命周期：
	1. 打开事件
	2. 消息事件
	3. 错误事件
	4. 关闭事件
	
### key words

Channel		

SocketChannel

HttpServerCodec

HttpObjectAggregator

ChunkedWriteHandler

SimpleChannelInboundHandler

WebSocketServerHandshaker  WebSocketServerHandshakerFactory

ChannelHandlerContext

FullHttpRequest  FullHttpResponse

HttpResponseStatus

```
"websocket".equals(request.headers().get("Upgrade"))
```

ChannelGroup  DefaultChannelGroup  GlobalEventExecutor

WebSocketFrame

PingWebSocketFrame  PongWebSocketFrame

CloseWebSocketFrame

TextWebSocketFrame

ChannelFuture

ChannelFutureListener

ByteBuf

Unpooled

CharsetUtil

EventLoopGroup NioEventLoopGroup

ServerBootstrap

NioServerSocketChannel

ChannelInitializer
	

### refer
IO的demo ： https://blog.csdn.net/anxpp/article/details/51512200
	
	