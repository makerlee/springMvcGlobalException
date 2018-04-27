package com.example.demo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * Created by lijiyang on 2018/4/17.
 */
public class ZKtest {
    public static void main(String[] args) throws Exception {
        CuratorFramework zkClient = CuratorFrameworkFactory
                .builder().connectString("172.20.66.159:2181")
                .namespace("test").retryPolicy(new RetryNTimes(2000, 20000)).build();
        zkClient.start();
        String nodeData = "hello";
        String hostNode = zkClient.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).
                forPath("/TEST_SERVER_", nodeData.getBytes("UTF-8"));

        System.out.println(hostNode);

        Stat stat = new Stat();
        System.out.println("节点数据：" + new String(zkClient.getData().storingStatIn(stat).forPath(hostNode)));
        System.out.println(stat.toString());
        zkClient.delete().deletingChildrenIfNeeded().withVersion(stat.getVersion()).forPath(hostNode);
        System.out.println("success delete znode " + zkClient);

    }
}
