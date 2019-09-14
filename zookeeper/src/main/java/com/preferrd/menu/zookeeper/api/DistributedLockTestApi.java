package com.preferrd.menu.zookeeper.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.preferrd.menu.zookeeper.lock.DistributedLockByZookeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Distributed lock test api
 * <p/>
 * Created in 2018.11.12
 * <p/>
 *
 * @author Liaozihong
 */
@RestController
public class DistributedLockTestApi {
    /**
     * The Distributed lock by zookeeper.
     */
    @Autowired
    DistributedLockByZookeeper distributedLockByZookeeper;
    private final static String PATH = "testv3";

    /**
     * Gets lock 1.
     *
     * @return the lock 1
     */
    @GetMapping("/lock1")
    public JSON getLock1() {
        Boolean flag = false;
        distributedLockByZookeeper.acquireDistributedLock(PATH);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLockByZookeeper.releaseDistributedLock(PATH);
        }
        flag = distributedLockByZookeeper.releaseDistributedLock(PATH);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put("result", "释放资源成功");
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject).getBody();
        } else {
            jsonObject.put("result", "释放资源失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject).getBody();
        }
    }

    /**
     * Gets lock 2.
     *
     * @return the lock 2
     */
    @GetMapping("/lock2")
    public JSON getLock2() {
        Boolean flag;
        distributedLockByZookeeper.acquireDistributedLock(PATH);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLockByZookeeper.releaseDistributedLock(PATH);
        }
        flag = distributedLockByZookeeper.releaseDistributedLock(PATH);
        JSONObject jsonObject = new JSONObject();
        if (flag) {
            jsonObject.put("result", "释放资源成功");
            return ResponseEntity.status(HttpStatus.OK).body(jsonObject).getBody();
        } else {
            jsonObject.put("result", "释放资源失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(jsonObject).getBody();
        }
    }
}
