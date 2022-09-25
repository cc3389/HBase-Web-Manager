package edu.wit.hbasemanager.manage.manageservice.HBaseTool;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.AsyncConnection;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * @Author Shawn Yue
 * @Description //TODO Shawn Yue
 * @Date 16:02 2022/9/21
 * @Param
 * @return
 **/
public class HBaseConnection {
    public static Connection connect () throws IOException {
        // 1. 创建配置对象
        Configuration conf = new Configuration();
        // 2. 添加配置参数
        conf.set("hbase.zookeeper.quorum","hadoop102,hadoop103,hadoop104");
        // 3. 创建 hbase 的连接
        // 默认使用同步连接
        Connection connection = ConnectionFactory.createConnection(conf);
        // 可以使用异步连接
        // 主要影响后续的 DML 操作
        CompletableFuture<AsyncConnection> asyncConnection = ConnectionFactory.createAsyncConnection(conf);
        // 4. 使用连接
        System.out.println(connection);
        // 5. 关闭连接
//        connection.close();
        return connection;
    }
}
