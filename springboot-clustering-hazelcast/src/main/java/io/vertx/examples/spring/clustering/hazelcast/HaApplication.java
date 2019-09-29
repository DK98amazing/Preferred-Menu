package io.vertx.examples.spring.clustering.hazelcast;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.hazelcast.core.HazelcastInstance;

import io.vertx.core.DeploymentOptions;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.ReplyException;
import io.vertx.core.impl.VertxImpl;
import io.vertx.core.json.JsonObject;
import io.vertx.core.spi.cluster.ClusterManager;

/**
 * @author Thomas Segismont
 */
@SpringBootApplication
public class HaApplication {

  private final static String CORE_LOCK = "coreLock";
  @Autowired
  HazelcastInstance hazelcastInstance;

  @Autowired
  Vertx vertx;

  public static void main(String[] args) {
    SpringApplication.run(HaApplication.class, args);
  }

  /**
   * Deploy verticles when the Spring application is ready.
   */
  @EventListener
  void deployVerticles(ApplicationReadyEvent event) {
    starHA(vertx);
  }

  private void starHA(Vertx vertx) {
    VertxImpl vertxImpl = (VertxImpl) vertx;
    ClusterManager clusterManager = (vertxImpl).getClusterManager();
    InetAddress ia=null;
    try {
      ia = InetAddress.getLocalHost();
      String localname=ia.getHostName();
      String localip=ia.getHostAddress();

      Map<String,String> map=hazelcastInstance.getMap("test");
      map.put(localname, localip);
      for(String key:map.keySet()){
        System.out.print("key:"+key);
        System.out.println("map:"+map.get(key));
      }
    } catch (UnknownHostException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    clusterManager.getLockWithTimeout(CORE_LOCK, 3000L, res -> {
      if (res.succeeded()) {
        vertx.eventBus().request(HelloVerticle.HELLO, "", ar -> {
          if (ar.failed() && (ar.cause() instanceof ReplyException && ((ReplyException) ar.cause()).failureType().name().equals("NO_HANDLERS"))) {
            DeploymentOptions options = new DeploymentOptions()
              .setHa(true);
            vertx.deployVerticle(HaVerticle.class.getName(), options);
          }
          String nodeId=clusterManager.getNodeID();
          startVertxClusterHelper(vertx,nodeId);
          vertx.deployVerticle(HelloVerticle.class.getName(), new DeploymentOptions().setHa(true));
          JsonObject config = new JsonObject().put("port", 8080);
          vertx.deployVerticle(new HttpVerticle(), new DeploymentOptions().setConfig(config));
        });
        res.result().release();
      } else {
        System.out.println("无法获取集群锁");
      }
    });
//    vertx.deployVerticle(new HelloVerticle());
//    JsonObject config = new JsonObject().put("port", 8080);
//    vertx.deployVerticle(new HttpVerticle(), new DeploymentOptions().setConfig(config));
  }
  public static void startVertxClusterHelper(Vertx vertx,String nodeId) {
    System.out.println("startVertxClusterHelper 。。。。。。。。。。。。");
    VertxClusterHelper.clusterNodeId =nodeId;
    JsonObject configs = new JsonObject();
    configs.put("nodeid",nodeId);
    DeploymentOptions deploymentOptions = new DeploymentOptions().setHa(true);
    deploymentOptions.setConfig(configs);
    vertx.deployVerticle(VertxClusterHelper.class.getName(), deploymentOptions);
  }
}
